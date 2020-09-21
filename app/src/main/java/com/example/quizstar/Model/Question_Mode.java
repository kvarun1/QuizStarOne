package com.example.quizstar.Model;

public class Question_Mode {
    String status;
    Question_Mode data;
    String game;
    String round;

    String a;
    String b,
            c,
            d,
            difficulty,
            primary_category,
            secondary_category,
            max_games, max_rounds, game_progress_percentage, round_progress_percentage, question_id, question;
    String message;

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

    public Question_Mode getData() {
        return data;
    }

    public void setData(Question_Mode data) {
        this.data = data;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getPrimary_category() {
        return primary_category;
    }

    public void setPrimary_category(String primary_category) {
        this.primary_category = primary_category;
    }

    public String getSecondary_category() {
        return secondary_category;
    }

    public void setSecondary_category(String secondary_category) {
        this.secondary_category = secondary_category;
    }

    public String getMax_games() {
        return max_games;
    }

    public void setMax_games(String max_games) {
        this.max_games = max_games;
    }

    public String getMax_rounds() {
        return max_rounds;
    }

    public void setMax_rounds(String max_rounds) {
        this.max_rounds = max_rounds;
    }

    public String getGame_progress_percentage() {
        return game_progress_percentage;
    }

    public void setGame_progress_percentage(String game_progress_percentage) {
        this.game_progress_percentage = game_progress_percentage;
    }

    public String getRound_progress_percentage() {
        return round_progress_percentage;
    }

    public void setRound_progress_percentage(String round_progress_percentage) {
        this.round_progress_percentage = round_progress_percentage;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
