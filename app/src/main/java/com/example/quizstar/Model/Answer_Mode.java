package com.example.quizstar.Model;

public class Answer_Mode {
    String status;
    Answer_Mode data;
    String id,
            correct,
            game,
            round,
            statuscomplete,
            max_games,
            max_rounds,
            game_progress_percentage,
            round_progress_percentage;
    String correct_answer;

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Answer_Mode getData() {
        return data;
    }

    public void setData(Answer_Mode data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
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

    public String getStatuscomplete() {
        return statuscomplete;
    }

    public void setStatuscomplete(String statuscomplete) {
        this.statuscomplete = statuscomplete;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;

}
