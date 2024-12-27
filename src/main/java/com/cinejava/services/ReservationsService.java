package com.cinejava.services;

import com.cinejava.constants.DataStoreConstants;
import com.cinejava.models.Reservation;

public class ReservationsService extends GenericService<Reservation> {
    public ReservationsService() {
        super(Reservation.class, DataStoreConstants.RESERVATION_STORE_NAME);
    }
}