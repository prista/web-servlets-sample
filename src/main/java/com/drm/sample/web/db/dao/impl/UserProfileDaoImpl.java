package com.drm.sample.web.db.dao.impl;

import com.drm.sample.web.db.dao.IUserProfileDao;
import com.drm.sample.web.db.exception.SQLExecutionException;
import com.drm.sample.web.db.model.UserProfile;

import java.sql.*;

public class UserProfileDaoImpl extends AbstractDaoImpl<UserProfile> implements IUserProfileDao {

    @Override
    public UserProfile createEntity() {
        return new UserProfile();
    }

    @Override
    protected UserProfile parseRow(final ResultSet resultSet) throws SQLException {
        UserProfile entity = createEntity();
        entity.setId((Integer) resultSet.getObject("id"));
        entity.setName(resultSet.getString("name"));
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
                             .format("insert into %s (name) values(?)", getTableName()),
                     Statement.RETURN_GENERATED_KEYS)) {
            c.setAutoCommit(false);
            try {
                pStmt.setString(1, entity.getName());


                pStmt.executeUpdate();

                final ResultSet rs = pStmt.getGeneratedKeys();
                rs.next();
                final int id = rs.getInt("id");

                rs.close();

                entity.setId(id);

                c.commit();
            } catch (final Exception e) {
                c.rollback();
                throw new RuntimeException(e);
            }
        } catch (final SQLException e) {
            throw new SQLExecutionException(e);
        }
    }

    @Override
    public UserProfile getFullInfo(final Integer id) {
        throw new UnsupportedOperationException("not implemented");
    }
}
