package com.drm.sample.web.db.dao;


import com.drm.sample.web.db.model.Car;

import java.sql.SQLException;
import java.util.List;

public interface ICarDao extends IBaseDao<Car>  {

    List<Car> geJoined() throws SQLException;

}
