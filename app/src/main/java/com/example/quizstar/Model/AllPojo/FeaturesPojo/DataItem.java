package com.example.quizstar.Model.AllPojo.FeaturesPojo;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("feature")
	private String feature;

	@SerializedName("id")
	private int id;

	@SerializedName("display_name")
	private String displayName;

	@SerializedName("enabled")
	private int enabled;

	public String getFeature(){
		return feature;
	}

	public int getId(){
		return id;
	}

	public String getDisplayName(){
		return displayName;
	}

	public int getEnabled(){
		return enabled;
	}
}