package com.cinejava.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cinejava.constants.DataStoreConstants;
import com.cinejava.models.Reservation;
import com.cinejava.services.interfaces.IReservationsService;

public class ReservationsService extends GenericService<Reservation> implements IReservationsService {
    public ReservationsService() {
        super(Reservation.class, DataStoreConstants.RESERVATION_STORE_NAME);
    }

    @Override
    public List<Reservation> getReservationsByUserId(long userId) {
        return getAll().stream()
            .filter(reservation -> reservation.getUserId() == userId)
            .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> getReservationsByMovieId(long movieId) {
        return getAll().stream()
            .filter(reservation -> reservation.getMovieId() == movieId)
            .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> getReservationsBySessionId(long sessionId) {
        return getAll().stream()
            .filter(reservation -> reservation.getSessionId() == sessionId)
            .collect(Collectors.toList());
    }

    @Override
    public Reservation createReservation(long userId, long movieId, long sessionId, List<Integer> seats) {
        if (seats == null || seats.isEmpty() || seats.stream().anyMatch(x -> x <= 0 || x > 40)) {
            throw new IllegalArgumentException("Reserved seats cannot be null, empty, or invalid");
        }
    
        Optional<Reservation> existingReservation = dataStoreContext.getAll().stream()
            .filter(reservation -> isMatchingReservation(reservation, userId, movieId, sessionId))
            .findFirst();
    
        if (existingReservation.isPresent()) {

            return getAndHandleExistingReservation(existingReservation.get(), seats);

        }
                
        Reservation newReservation = new Reservation(movieId, userId, sessionId, seats);
        dataStoreContext.insert(newReservation);
        return newReservation;
    }
    
    @Override        
    public Reservation mergeReservations(Reservation baseReservation, Reservation newReservation) {
        List<Integer> combinedSeats = baseReservation.getReservedSeats();
        combinedSeats.addAll(newReservation.getReservedSeats());
        baseReservation.setReservedSeats(combinedSeats);
        update(baseReservation.id, baseReservation);
        return baseReservation;
    }

    @Override
    public boolean cancelReservation(long reservationId) {
        Optional<Reservation> reservation = get(reservationId);
        if (reservation.isPresent()) {
            delete(reservationId);
            return true;
        }
        return false;
    }

    private Reservation getAndHandleExistingReservation(Reservation existingReservation, List<Integer> seats) {
        Reservation newReservation = existingReservation;
        List<Integer> existingSeats = newReservation.getReservedSeats();

        boolean hasOverlap = seats.stream().anyMatch(existingSeats::contains);
        if (hasOverlap) {
            throw new IllegalArgumentException("One or more seats are already reserved by this user");
        }

        existingSeats.addAll(seats);
        newReservation.setReservedSeats(existingSeats);
        dataStoreContext.update(newReservation.id, newReservation);
        return newReservation;
    }

    private boolean isMatchingReservation(Reservation reservation, long userId, long movieId, long sessionId) {
        return reservation.getUserId() == userId &&
               reservation.getMovieId() == movieId &&
               reservation.getSessionId() == sessionId;
    }
}