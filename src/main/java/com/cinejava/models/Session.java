package com.cinejava.models;

import java.time.LocalDateTime;

import com.cinejava.common.BaseModel;

public class Session extends BaseModel {
    private LocalDateTime startTime;    // Seans başlangıç zamanı
    private LocalDateTime endTime;      // Seans bitiş zamanı
}
