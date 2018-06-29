package com.drm.sample.web;

import com.drm.sample.web.db.dao.IResourceDao;
import com.drm.sample.web.db.dao.IUserProfileDao;
import com.drm.sample.web.db.dao.impl.ResourceDaoImpl;
import com.drm.sample.web.db.dao.impl.UserProfileDaoImpl;
import com.drm.sample.web.db.model.Resource;
import com.drm.sample.web.db.model.UserProfile;

import java.util.Random;

public abstract class AbstractTest {

    private final IUserProfileDao userProfileDao = new UserProfileDaoImpl();
    private final IResourceDao resourceDao = new ResourceDaoImpl();

    private final Random RANDOM = new Random();

    protected String getRandomPrefix() {
        return RANDOM.nextInt(99999) + "";
    }

    protected int getRandomObjectsCount() {
        return RANDOM.nextInt(9) + 1;
    }

    public IUserProfileDao getUserProfileDao() {
        return userProfileDao;
    }

    public IResourceDao getResourceDao() {
        return resourceDao;
    }

    protected UserProfile saveNewUserProfile() {
        final UserProfile entity = userProfileDao.createEntity();
        entity.setName("brand-" + getRandomPrefix());
        entity.setPassword("password-" + getRandomPrefix());
        userProfileDao.insert(entity);
        return entity;
    }

    protected Resource saveNewResource() {
        final Resource entity = resourceDao.createEntity();
        entity.setPath("/res" + getRandomPrefix());
        resourceDao.insert(entity);
        return entity;
    }

}
