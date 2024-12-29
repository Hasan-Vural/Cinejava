package com.cinejava.models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.cinejava.common.BaseModel;

public class Session extends BaseModel {
    private LocalDate sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Session(LocalDate sessionDate, LocalTime startTime, LocalTime endTime) {
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Session() {
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
