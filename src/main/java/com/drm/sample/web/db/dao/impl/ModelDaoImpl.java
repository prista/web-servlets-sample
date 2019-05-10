package com.drm.sample.web.db.dao.impl;

import com.drm.sample.web.db.dao.IModelDao;
import com.drm.sample.web.db.model.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelDaoImpl extends AbstractDao<Model> implements IModelDao {

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
		throw new RuntimeException("not implemented");
		
	}

	@Override
	protected String getTableName() {
		return "model";
	}
	

}
