package com.example.quizstar.Model;

public class Score {


    String status;
    Score data;
    String game, round;
    String max_games,
            max_rounds,
            game_progress_percentage,
            round_progress_percentage,
            win_dollars,
            win_quora, last_played;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Score getData() {
        return data;
    }

    public void setData(Score data) {
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

    public String getWin_dollars() {
        return win_dollars;
    }

    public void setWin_dollars(String win_dollars) {
        this.win_dollars = win_dollars;
    }

    public String getWin_quora() {
        return win_quora;
    }

    public void setWin_quora(String win_quora) {
        this.win_quora = win_quora;
    }

    public String getLast_played() {
        return last_played;
    }

    public void setLast_played(String last_played) {
        this.last_played = last_played;
    }
}
