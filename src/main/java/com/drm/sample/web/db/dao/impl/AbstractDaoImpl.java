package com.drm.sample.web.db.dao.impl;

import com.drm.sample.web.db.dao.IBaseDao;
import com.drm.sample.web.db.exception.SQLExecutionException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public abstract class AbstractDaoImpl<T> implements IBaseDao<T> {

    private static final Properties DB = new Properties();

    static {
        try (final InputStream stream = AbstractDaoImpl.class.getClassLoader()
                .getResourceAsStream("jdbc-web-auth.properties")) {
            DB.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> getAll() {
        try (Connection c = getConnection();
             Statement stmt = c.createStatement()) {

            stmt.executeQuery("select * from " + getTableName());

            ResultSet resultSet = stmt.getResultSet();

            List<T> result = new ArrayList<>();
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

    public T getById(final Integer id) {
        try (Connection c = getConnection();
             Statement stmt = c.createStatement()) {

            stmt.executeQuery(
                    String.format("select * from %s where id=%s", getTableName(), id));

            ResultSet resultSet = stmt.getResultSet();
            boolean hasNext = resultSet.next();
            T result = null;
            if (hasNext) {
                result = parseRow(resultSet);
            }

            resultSet.close();
            return result;

        } catch (final SQLException e) {
            throw new SQLExecutionException(e);
        }
    }

    public void delete(final Integer id) {
        try (Connection c = getConnection();
             PreparedStatement pStmt = c.prepareStatement(
                     String.format("delete from %s where id=?", getTableName()))) {
            c.setAutoCommit(false);
            try {
                pStmt.setObject(1, id);
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

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB.getProperty("db.url"), DB.getProperty("db.user"),
                DB.getProperty("db.password"));
    }

    protected abstract T parseRow(ResultSet resultSet) throws SQLException;

    protected abstract String getTableName();
}
