package com.example.quizstar.Model;

import java.io.Serializable;

public class Rank implements Serializable {


    String status;
    String rank;
    String win_ratio, average;
    Rank data;

    public Rank getData() {
        return data;
    }

    public void setData(Rank data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getWin_ratio() {
        return win_ratio;
    }

    public void setWin_ratio(String win_ratio) {
        this.win_ratio = win_ratio;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

}
