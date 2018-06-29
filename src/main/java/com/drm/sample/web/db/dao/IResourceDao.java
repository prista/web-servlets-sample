package com.drm.sample.web.db.dao;

import com.drm.sample.web.db.model.Resource;

import java.util.Set;

public interface IResourceDao extends IBaseDao<Resource> {

    Set<Resource> getByUserProfile(Integer id);

    void deleteM2M();
}
