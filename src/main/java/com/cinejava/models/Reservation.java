package com.cinejava.models;

import java.util.List;

import com.cinejava.common.BaseModel;

public class Reservation extends BaseModel {
    private long movieId;
    private long userId;
    private long sessionId;
    private List<Integer> reservedSeats;
    
    public Reservation(long movieId, long userId, long sessionId, List<Integer> reservedSeats) {
        this.movieId = movieId;
        this.userId = userId;
        this.sessionId = sessionId;
        this.reservedSeats = reservedSeats;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public List<Integer> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<Integer> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    
}
