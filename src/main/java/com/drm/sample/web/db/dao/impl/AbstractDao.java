package com.drm.sample.web.db.dao.impl;

import com.drm.sample.web.db.dao.IBaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements IBaseDao<T> {

    @Override
    public T getById(Integer id) throws SQLException {
        Connection c = getConnection();

        Statement statement = c.createStatement();
        statement.executeQuery("select * from " + getTableName() + " where id=" + id);

        ResultSet resultSet = statement.getResultSet();
        boolean hasNext = resultSet.next();
        T result = null;
        if (hasNext) {
            result = handleRow(resultSet);
        }

        resultSet.close();
        statement.close();
        c.close();

        return result;
    }

    public List<T> getAll() throws SQLException {
        Connection c = getConnection();

        Statement statement = c.createStatement();
        statement.executeQuery("select * from " + getTableName());

        ResultSet resultSet = statement.getResultSet();

        List<T> result = new ArrayList<>();
        boolean hasNext = resultSet.next();
        while (hasNext) {
            result.add(handleRow(resultSet));
            hasNext = resultSet.next();
        }

        resultSet.close();
        statement.close();
        c.close();

        return result;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Connection c = getConnection();

        Statement statement = c.createStatement();
        statement.executeUpdate(
                String.format("delete from %s where id=%s", getTableName(), id));

        statement.close();
        c.close();
    }

    protected Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/pristadb", "postgres", "pgpass");
        return c;
    }

    protected abstract T handleRow(ResultSet resultSet) throws SQLException;

    protected abstract String getTableName();
}
