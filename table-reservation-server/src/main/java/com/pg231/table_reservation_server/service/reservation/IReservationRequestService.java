package com.pg231.table_reservation_server.service.reservation;

import com.pg231.table_reservation_server.model.ReservationRequest;

import java.util.List;

public interface IReservationRequestService {

    /**
     * Retrieves all reservation requests.
     *
     * @return a list of sorted reservations
     */
    List<ReservationRequest> findAll();

    /**
     * Creates a new reservation request and adds it to the queue.
     *
     * @param request the reservation request to create
     * @return the created reservation request
     */
    ReservationRequest createReservationRequest(ReservationRequest request);

    /**
     * Retrieves the head reservation request without removing it from the queue.
     *
     * @return the head reservation request, or null if the queue is empty
     */
    ReservationRequest getNextReservationRequest();

    /**
     * Processes (removes) the head reservation request from the queue.
     *
     * @return the removed reservation request, or null if the queue is empty
     */
    ReservationRequest processNextReservationRequest();

    /**
     * Deletes a reservation request by ID from both the queue and the repository.
     *
     * @param reservationRequestId the ID of the reservation request to delete
     * @return true if deleted successfully, false otherwise
     */
    boolean deleteReservationRequest(String reservationRequestId);
}
