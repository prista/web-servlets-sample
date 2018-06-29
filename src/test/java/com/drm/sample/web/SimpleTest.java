package com.drm.sample.web;

import com.drm.sample.web.db.model.Resource;
import com.drm.sample.web.db.model.UserProfile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimpleTest extends AbstractTest {

    @Before
    public void cleanTables() {
        getResourceDao().deleteM2M(); // it's very important
        getResourceDao().deleteAll();
        getUserProfileDao().deleteAll();
    }

    @Test
    public void createUserProfileWithResourcesTest() {
        final UserProfile entity = getUserProfileDao().createEntity();
        entity.setName("user-" + getRandomPrefix());
        entity.setPassword("password-" + getRandomPrefix());

        final int randomObjectsCount = getRandomObjectsCount();
        final List<Resource> resourceList = new ArrayList<>();
        for (int i = 0; i < randomObjectsCount; i++) {
            resourceList.add(saveNewResource());
        }
        entity.getAllowedResources().addAll(resourceList);

        getUserProfileDao().insert(entity);

        final UserProfile entityFromDB = getUserProfileDao().getFullInfo(entity.getId());
        final Set<Resource> resourcesFromDB = entityFromDB.getAllowedResources();
        assertEquals(resourceList.size(), resourcesFromDB.size());

        // check that correct (by id) engines were returned
        for (final Resource engine : resourceList) {
            boolean found = false;
            for (final Resource dbResource : resourcesFromDB) {
                if (engine.getId().equals(dbResource.getId())) {
                    found = true;
                    break;
                }

            }
            assertTrue("can't find entity:" + engine, found);
        }
    }

    @Test
    public void testDeleteAll() {
        saveNewUserProfile();
        getUserProfileDao().deleteAll();
        assertEquals(0, getResourceDao().getAll().size());
    }
}
