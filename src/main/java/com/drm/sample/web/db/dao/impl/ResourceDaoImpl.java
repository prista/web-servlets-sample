package com.drm.sample.web.db.dao.impl;

import com.drm.sample.web.db.dao.IResourceDao;
import com.drm.sample.web.db.exception.SQLExecutionException;
import com.drm.sample.web.db.model.Resource;

import java.sql.*;

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
}
