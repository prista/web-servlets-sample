package com.drm.sample.web.db.model;

public class Resource {

    private Integer id;

    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
