package com.drm.sample.web.db.dao.impl;


import com.drm.sample.web.db.dao.ICarDao;
import com.drm.sample.web.db.model.Car;
import com.drm.sample.web.db.model.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl extends AbstractDao<Car> implements ICarDao {

	@Override
	protected Car handleRow(ResultSet resultSet) throws SQLException {
		Car car = new Car();
		car.setModelId((Integer) resultSet.getObject("model_id"));
		car.setId(resultSet.getInt("id"));
		car.setYear((Integer) resultSet.getObject("year"));
		car.setEngineType(resultSet.getString("engine_type"));
		return car;
	}

	@Override
	protected String getTableName() {
		return "car";
	}

	@Override
	public Integer insert(Car object) throws SQLException {
		throw new RuntimeException("not implemented");
	}

	@Override
	public List<Car> geJoined() throws SQLException {
		Connection c = getConnection();

		Statement statement = c.createStatement();
		statement.executeQuery("select c.id, c.engine_type, c.year, c.model_id, m.name as modelName from car c\n" +
				"                     join model m on c.model_id = m.id\n" +
				"                     join brand b on m.brand_id = b.id");

		ResultSet resultSet = statement.getResultSet();

		List<Car> result = new ArrayList<>();
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
}
