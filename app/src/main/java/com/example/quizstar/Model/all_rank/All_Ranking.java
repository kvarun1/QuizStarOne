package com.example.quizstar.Model.all_rank;

import com.example.quizstar.Model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class All_Ranking {
    @SerializedName("won")
    @Expose
    private String won;
    @SerializedName("lost")
    @Expose
    private String  lost;
    @SerializedName("ratio")
    @Expose
    private String ratio;
    @SerializedName("max_score")
    @Expose
    private String maxScore;
    @SerializedName("average_score")
    @Expose
    private String averageScore;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("position")
    @Expose
    private int position;

    public String getWon() {
        return won;
    }

    public void setWon(String won) {
        this.won = won;
    }

    public String getLost() {
        return lost;
    }

    public void setLost(String lost) {
        this.lost = lost;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
    }

    public String getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(String averageScore) {
        this.averageScore = averageScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
