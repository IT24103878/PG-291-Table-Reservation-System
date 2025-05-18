package com.pg291.table_reservation_server.controller;

import com.pg291.table_reservation_server.model.ReservationRequest;
import com.pg291.table_reservation_server.service.reservation.IReservationRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation/requests")
public class ReservationRequestController {

    private final IReservationRequestService reservationRequestService;

    public ReservationRequestController(IReservationRequestService reservationRequestService) {
        this.reservationRequestService = reservationRequestService;
    }

    /**
     * Retrieves all reservation requests.
     *
     * @return list of all reservation requests
     */
    @GetMapping
    public ResponseEntity<List<ReservationRequest>> getAll() {
        return ResponseEntity.ok(reservationRequestService.findAll());
    }

    /**
     * Creates a new reservation request and adds it to the queue.
     *
     * @param reservationRequest the reservation request to be created
     * @return the created reservation request
     */
    @PostMapping
    public ResponseEntity<ReservationRequest> create(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.ok(reservationRequestService.createReservationRequest(reservationRequest));
    }

    /**
     * Retrieves the reservation request at the head of the queue without removing it.
     *
     * @return the head reservation request, or 204 No Content if the queue is empty
     */
    @GetMapping("/next")
    public ResponseEntity<ReservationRequest> getNextReservationRequest() {
        ReservationRequest next = reservationRequestService.getNextReservationRequest();
        return (next != null)
                ? ResponseEntity.ok(next)
                : ResponseEntity.noContent().build();
    }

    /**
     * Processes (removes) the reservation request at the head of the queue.
     *
     * @return the removed reservation request
     */
    @PostMapping("/process-next")
    public ResponseEntity<ReservationRequest> processNextReservationRequest() {
        return ResponseEntity.ok(reservationRequestService.processNextReservationRequest());
    }

    /**
     * Deletes a reservation request by its ID.
     *
     * @param id the ID of the reservation request to delete
     * @return 204 No Content if deleted successfully, or 404 Not Found if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return reservationRequestService.deleteReservationRequest(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
