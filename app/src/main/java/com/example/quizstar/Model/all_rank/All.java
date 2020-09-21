package com.example.quizstar.Model.all_rank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class All {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private All_Screen_Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public All_Screen_Data getData() {
        return data;
    }

    public void setData(All_Screen_Data data) {
        this.data = data;
    }

}

