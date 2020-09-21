package com.example.quizstar.Model;

public class End {
    String status;
    String message;
    End data;

    public End getData() {
        return data;
    }

    public void setData(End data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
