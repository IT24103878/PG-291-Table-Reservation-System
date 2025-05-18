package com.pg291.table_reservation_server.service.reservation;

import com.pg291.table_reservation_server.model.Reservation;

import java.util.List;

/**
 * Interface for reservation-related operations.
 */
public interface IReservationService {

    /**
     * Creates a new reservation and marks the associated table as unavailable.
     *
     * @param reservation the reservation to be created
     * @return the created reservation
     */
    Reservation createReservation(Reservation reservation);

    /**
     * Marks a reservation as completed and makes the associated table available again.
     *
     * @param id the ID of the reservation to complete
     */
    void markReservationAsComplete(String id);

    /**
     * Retrieves all reservations sorted using merge sort.
     *
     * @return a list of sorted reservations
     */
    List<Reservation> findAll();
}
