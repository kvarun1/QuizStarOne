package com.example.quizstar.Model;

import java.io.Serializable;

public class Login implements Serializable {
    String message,status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
