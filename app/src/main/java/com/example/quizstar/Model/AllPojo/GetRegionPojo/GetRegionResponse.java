package com.example.quizstar.Model.AllPojo.GetRegionPojo;

import com.google.gson.annotations.SerializedName;

public class GetRegionResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("status")
	private String status;

	public Data getData(){
		return data;
	}

	public String getStatus(){
		return status;
	}
}