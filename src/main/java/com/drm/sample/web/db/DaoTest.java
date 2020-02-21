package com.drm.sample.web.db;

import com.drm.sample.web.db.dao.IBrandDao;
import com.drm.sample.web.db.dao.ICarDao;
import com.drm.sample.web.db.dao.IModelDao;
import com.drm.sample.web.db.dao.impl.BrandDaoImpl;
import com.drm.sample.web.db.dao.impl.CarDaoImpl;
import com.drm.sample.web.db.dao.impl.ModelDaoImpl;
import com.drm.sample.web.db.model.Brand;

import java.sql.SQLException;
import java.util.List;

public class DaoTest {

    public static void main(String[] args) throws SQLException {
        IBrandDao brandDao = BrandDaoImpl.INSTANCE;
        IModelDao modelDao = new ModelDaoImpl();
        ICarDao carDao = new CarDaoImpl();

        printList(brandDao.getAll());
        printList(modelDao.getAll());

        Brand object = new Brand();
        object.setName("new brand 1");
        ;
        Integer generatedId = brandDao.insert(object);
        System.out.println("latest generated brand id:" + generatedId);
        printList(brandDao.getAll());
        printList(carDao.geJoined());

    }

    private static void printList(List<? extends Object> all) {
        for (Object object : all) {
            System.out.println(object.toString());
        }
    }
}
