package com.drm.sample.web.db.dao.impl;

import com.drm.sample.web.db.dao.IModelDao;
import com.drm.sample.web.db.model.Model;

import java.sql.*;

public class ModelDaoImpl extends AbstractDao<Model> implements IModelDao {

	// esli ne delat singleton, to constructor vyzyvaetsia kazhdy raz pri F5 i novom user thread
	public ModelDaoImpl() {
		System.out.println("I'm CREATED " + getClass().getSimpleName());
	}

	@Override
	protected Model handleRow(ResultSet resultSet) throws SQLException {
		Model model = new Model();
		model.setBrandId((Integer) resultSet.getObject("brand_id"));
		model.setId(resultSet.getInt("id"));
		model.setName(resultSet.getString("name"));
		return model;

	}
	
	
	@Override
	public Integer insert(Model object) throws SQLException {
		Connection c = getConnection();

		PreparedStatement preparedStatement = c.prepareStatement(
				"insert into model (name, brand_id) values(?,?)", Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, object.getName());
		preparedStatement.setInt(2, object.getBrandId());

		preparedStatement.executeUpdate();

		final ResultSet rs = preparedStatement.getGeneratedKeys();
		rs.next();
		final int id = rs.getInt("id");

		rs.close();
		preparedStatement.close();
		c.close();

		return id;
		
	}

	@Override
	protected String getTableName() {
		return "model";
	}
	

}
