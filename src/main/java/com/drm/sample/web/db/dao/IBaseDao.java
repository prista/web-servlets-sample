package com.drm.sample.web.db.dao;

import java.util.List;

public interface IBaseDao<T> {

    T createEntity();

    void insert(T entity);

    List<T> getAll();

    T getById(Integer id);

    void delete(Integer id);

}
