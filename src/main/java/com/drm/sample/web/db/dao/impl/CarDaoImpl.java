package com.drm.sample.web.db.dao.impl;


import com.drm.sample.web.db.dao.ICarDao;
import com.drm.sample.web.db.model.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDaoImpl extends AbstractDao<Car> implements ICarDao {

	@Override
	protected Car handleRow(ResultSet resultSet) throws SQLException {
		throw new RuntimeException("not implemented");
	}

	@Override
	protected String getTableName() {
		return "car";
	}

	@Override
	public Integer insert(Car object) throws SQLException {
		throw new RuntimeException("not implemented");
		
	}
}
