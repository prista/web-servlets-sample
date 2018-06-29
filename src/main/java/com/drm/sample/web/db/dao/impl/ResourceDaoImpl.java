package com.drm.sample.web.db.dao.impl;

import com.drm.sample.web.db.dao.IResourceDao;
import com.drm.sample.web.db.exception.SQLExecutionException;
import com.drm.sample.web.db.model.Resource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ResourceDaoImpl extends AbstractDaoImpl<Resource> implements IResourceDao {

    @Override
    public Resource createEntity() {
        return new Resource();
    }

    @Override
    protected Resource parseRow(final ResultSet resultSet) throws SQLException {
        Resource entity = createEntity();
        entity.setId((Integer) resultSet.getObject("id"));
        entity.setPath(resultSet.getString("path"));
        return entity;
    }

    @Override
    protected String getTableName() {
        return "resource";
    }

    @Override
    public void insert(final Resource entity) {
        try (Connection c = getConnection();
             PreparedStatement pStmt = c.prepareStatement(String
                             .format("insert into %s (path) values(?)", getTableName()),
                     Statement.RETURN_GENERATED_KEYS)) {
            c.setAutoCommit(false);
            try {
                pStmt.setString(1, entity.getPath());


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
    public Set<Resource> getByUserProfile(final Integer id) {
        try (Connection c = getConnection();
             Statement stmt = c.createStatement()) {


            stmt.executeQuery(
                    String.format(
                            "select * from resource r "
                                    + "inner join user_profile_2_resource u2r on r.id=u2r.resource_id "
                                    + "where u2r.user_profile_id=%s", id));

            ResultSet resultSet = stmt.getResultSet();

            final Set<Resource> result = new HashSet<>();
            boolean hasNext = resultSet.next();
            while (hasNext) {
                result.add(parseRow(resultSet));
                hasNext = resultSet.next();
            }
            resultSet.close();
            return result;

        } catch (final SQLException e) {
            throw new SQLExecutionException(e);
        }
    }

    @Override
    public void deleteM2M() {
        try (Connection c = getConnection();
             PreparedStatement pStmt = c.prepareStatement(
                     "delete from user_profile_2_resource")) {
            c.setAutoCommit(false);
            try {
                pStmt.executeUpdate();
                c.commit();
            } catch (final Exception e) {
                c.rollback();
                throw new RuntimeException(e);
            }

        } catch (final SQLException e) {
            throw new SQLExecutionException(e);
        }
    }
}
