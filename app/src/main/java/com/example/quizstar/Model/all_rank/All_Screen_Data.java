package com.example.quizstar.Model.all_rank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class All_Screen_Data {
    @SerializedName("ranking")
    @Expose
    private List<All_Ranking> ranking = null;

    public List<All_Ranking> getRanking() {
        return ranking;
    }

    public void setRanking(List<All_Ranking> ranking) {
        this.ranking = ranking;
    }
}