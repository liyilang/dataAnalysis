package com.controller;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
	
	String asset;
	
	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public String  execute() {
		
		System.out.println(asset);
		 return SUCCESS;
	}
}
