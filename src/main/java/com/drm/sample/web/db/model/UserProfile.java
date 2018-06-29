package com.drm.sample.web.db.model;

public class UserProfile {

    private Integer id;

    private String name;

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

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
