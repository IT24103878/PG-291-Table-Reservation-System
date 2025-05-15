package com.pg231.table_reservation_server.service.reservation;

import com.pg231.table_reservation_server.model.ReservationRequest;
import com.pg231.table_reservation_server.queue.ReservationRequestQueue;
import com.pg231.table_reservation_server.repository.ReservationRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationRequestService implements IReservationRequestService{

    private final ReservationRequestQueue queue;
    private final ReservationRequestRepository reservationRequestRepository;

    /**
     * Retrieves all reservation requests.
     *
     * @return a list of reservation requests
     */
    @Override
    public List<ReservationRequest> findAll() {
        return reservationRequestRepository.findAll();
    }

    /**
     * Constructs a new ReservationRequestService.
     * Initializes the queue with existing reservation requests from the repository.
     */
    public ReservationRequestService() {
        this.queue = new ReservationRequestQueue();
        this.reservationRequestRepository = new ReservationRequestRepository();

        // Get all saved reservation requests
        List<ReservationRequest> reservationRequests = this.reservationRequestRepository.findAll();

        // Add reservation to the queue
        reservationRequests.forEach(queue::enqueue);
    }

    /**
     * Creates a new reservation request, adds it to the repository and the queue.
     *
     * @param request the reservation request to create
     * @return the created reservation request
     */
    @Override
    public ReservationRequest createReservationRequest(ReservationRequest request) {
        // Set the date time
        request.setDateTime(LocalDateTime.now());
        ReservationRequest addedReservationRequest = reservationRequestRepository.save(request);

        // Add the created reservation request to queue
        queue.enqueue(addedReservationRequest);
        return addedReservationRequest;
    }

    /**
     * Retrieves the head reservation request from the queue without removing it.
     *
     * @return the head reservation request, or null if the queue is empty
     */
    @Override
    public ReservationRequest getNextReservationRequest() {
       return queue.peek();
    }

    /**
     * Processes and removes the head reservation request from the queue.
     *
     * @return the removed reservation request, or null if the queue is empty
     */
    @Override
    public ReservationRequest processNextReservationRequest() {
       ReservationRequest nextReservationRequest = queue.dequeue();
       reservationRequestRepository.delete(nextReservationRequest.getId());
       return nextReservationRequest;
    }

    /**
     * Deletes a reservation request by ID from both the queue and the repository.
     *
     * @param reservationRequestId the ID of the reservation request to delete
     * @return true if deleted successfully, false otherwise
     */
    @Override
    public boolean deleteReservationRequest(String reservationRequestId) {
        boolean removed = queue.removeById(reservationRequestId);
        if (removed) {
            reservationRequestRepository.delete(reservationRequestId);
        }
        return removed;
    }
}
