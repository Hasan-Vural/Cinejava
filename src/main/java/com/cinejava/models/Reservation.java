package com.cinejava.models;

import java.time.LocalDateTime;
import java.util.List;

import com.cinejava.common.BaseModel;

public class Reservation extends BaseModel {
    private long movieId;
    private long userId;
    private long sessionId;
    private List<Integer> reservedSeats;
}
