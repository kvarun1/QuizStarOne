package com.example.quizstar.Model.AllPojo.FeaturesPojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FeaturesResponse{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("status")
	private String status;

	public List<DataItem> getData(){
		return data;
	}

	public String getStatus(){
		return status;
	}

/*	String id, feature,
			display_name,
			enabled;*/
}