package com.drm.sample.web.db.dao.impl;

import com.drm.sample.web.db.dao.IResourceDao;
import com.drm.sample.web.db.dao.IUserProfileDao;
import com.drm.sample.web.db.exception.SQLExecutionException;
import com.drm.sample.web.db.model.Resource;
import com.drm.sample.web.db.model.UserProfile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserProfileDaoImpl extends AbstractDaoImpl<UserProfile> implements IUserProfileDao {

    private IResourceDao resourceDao = new ResourceDaoImpl();

    @Override
    public UserProfile createEntity() {
        return new UserProfile();
    }

    @Override
    protected UserProfile parseRow(final ResultSet resultSet) throws SQLException {
        UserProfile entity = createEntity();
        entity.setId((Integer) resultSet.getObject("id"));
        entity.setName(resultSet.getString("name"));
        entity.setPassword(resultSet.getString("password"));
        return entity;
    }

    @Override
    protected String getTableName() {
        return "user_profile";
    }


    @Override
    public void insert(final UserProfile entity) {
        try (Connection c = getConnection();
             PreparedStatement pStmt = c.prepareStatement(String
                             .format("insert into %s (name, password) values(?, ?)", getTableName()),
                     Statement.RETURN_GENERATED_KEYS)) {
            c.setAutoCommit(false);
            try {
                pStmt.setString(1, entity.getName());
                pStmt.setString(2, entity.getPassword());


                pStmt.executeUpdate();

                final ResultSet rs = pStmt.getGeneratedKeys();
                rs.next();
                final int id = rs.getInt("id");

                rs.close();

                entity.setId(id);
                updateResources(entity, c);

                c.commit();
            } catch (final Exception e) {
                c.rollback();
                throw new RuntimeException(e);
            }
        } catch (final SQLException e) {
            throw new SQLExecutionException(e);
        }
    }

    private void updateResources(final UserProfile entity, final Connection c) throws SQLException {
        // clear all existing records related to the current model
        final PreparedStatement deleteStmt = c.prepareStatement("delete from user_profile_2_resource where user_profile_id=?");
        deleteStmt.setInt(1, entity.getId());
        deleteStmt.executeUpdate();
        deleteStmt.close();

        if (entity.getAllowedResources().isEmpty()) {
            return;
        }

        // insert actual list of records using 'batch'
        final PreparedStatement pStmt = c
                .prepareStatement("insert into user_profile_2_resource (user_profile_id, resource_id) values(?,?)");

        for (final Resource e : entity.getAllowedResources()) {
            pStmt.setInt(1, entity.getId());
            pStmt.setInt(2, e.getId());
            pStmt.addBatch();
        }
        pStmt.executeBatch();
        pStmt.close();
    }

    @Override
    public UserProfile getFullInfo(final Integer id) {
        final UserProfile userProfile = getById(id);
        final Set<Resource> resources = resourceDao.getByUserProfile(id);
        userProfile.setAllowedResources(resources);

        return userProfile;
    }
}
