package com.drm.sample.web.db.model;

import java.util.HashSet;
import java.util.Set;

public class UserProfile {

    private Integer id;

    private String name;

    private String password;

    private Set<Resource> allowedResources = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Set<Resource> getAllowedResources() {
        return allowedResources;
    }

    public void setAllowedResources(final Set<Resource> allowedResources) {
        this.allowedResources = allowedResources;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", allowedResources=" + allowedResources +
                '}';
    }
}
