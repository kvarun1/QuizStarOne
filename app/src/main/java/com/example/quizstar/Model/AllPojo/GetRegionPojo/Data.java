package com.example.quizstar.Model.AllPojo.GetRegionPojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("regions")
	private List<String> regions;

	public List<String> getRegions(){
		return regions;
	}
}