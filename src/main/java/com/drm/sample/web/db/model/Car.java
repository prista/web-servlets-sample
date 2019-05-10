package com.drm.sample.web.db.model;

public class Car {

	private Integer id;
	private String engineType;
	private Integer year;
	private Integer modelId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", engineType=" + engineType + ", year=" + year
				+ ", modelId=" + modelId + "]";
	}
	
	
}
