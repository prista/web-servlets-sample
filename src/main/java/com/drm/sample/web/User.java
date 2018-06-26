package com.drm.sample.web;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String password;
	
	private List <String> allowedResources=new ArrayList<String>();

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getAllowedResources() {
		return allowedResources;
	}

}
