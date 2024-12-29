package com.cinejava.singletons;

import com.cinejava.models.Reservation;

public final class ReservationInstanceSingleton {
  
    private Reservation reservation;
    private final static ReservationInstanceSingleton INSTANCE = new ReservationInstanceSingleton();
    
    private ReservationInstanceSingleton() {}
    
    public static ReservationInstanceSingleton getInstance() {
      return INSTANCE;
    }
    
    public void setReservation(Reservation reservation) {
      this.reservation = reservation;
    }
    
    public Reservation getReservation() {
      return this.reservation;
    }
  }
