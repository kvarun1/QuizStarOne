package com.example.quizstar.Model;

import com.example.quizstar.Model.AllPojo.FeaturesPojo.DataItem;

import java.util.List;

public class Sent_Data {
    String status;
    List<Sent_Data> data;
    Sent_Data from;
    User user;
    String state, bet_amount, versus, won, lost, ratio, max_score, average_score, position, id;

    Sent_Data to;
    Sent_Data progress;
    String game, round, rank;

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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBet_amount() {
        return bet_amount;
    }

    public void setBet_amount(String bet_amount) {
        this.bet_amount = bet_amount;
    }

    public String getVersus() {
        return versus;
    }

    public void setVersus(String versus) {
        this.versus = versus;
    }

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

    public String getMax_score() {
        return max_score;
    }

    public void setMax_score(String max_score) {
        this.max_score = max_score;
    }

    public String getAverage_score() {
        return average_score;
    }

    public void setAverage_score(String average_score) {
        this.average_score = average_score;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sent_Data getProgress() {
        return progress;
    }

    public void setProgress(Sent_Data progress) {
        this.progress = progress;
    }

    public Sent_Data getTo() {
        return to;
    }

    public void setTo(Sent_Data to) {
        this.to = to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Sent_Data> getData() {
        return data;
    }

    public void setData(List<Sent_Data> data) {
        this.data = data;
    }

    public Sent_Data getFrom() {
        return from;
    }

    public void setFrom(Sent_Data from) {
        this.from = from;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
