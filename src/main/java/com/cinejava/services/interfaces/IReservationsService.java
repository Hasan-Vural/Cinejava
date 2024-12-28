package com.cinejava.services.interfaces;

import java.util.List;

import com.cinejava.models.Reservation;

public interface IReservationsService extends IGenericService<Reservation> {
    List<Reservation> getReservationsByUserId(long userId);
    List<Reservation> getReservationsByMovieId(long movieId);
    List<Reservation> getReservationsBySessionId(long sessionId);
    Reservation createReservation(long userId, long movieId, long sessionId, List<Integer> seats);
    Reservation mergeReservations(Reservation baseReservation, Reservation newReservation);
    boolean cancelReservation(long reservationId);
}
