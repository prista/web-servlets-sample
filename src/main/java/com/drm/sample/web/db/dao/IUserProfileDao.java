package com.drm.sample.web.db.dao;

import com.drm.sample.web.db.model.UserProfile;

public interface IUserProfileDao extends IBaseDao<UserProfile> {

    UserProfile getFullInfo(Integer id);

}
