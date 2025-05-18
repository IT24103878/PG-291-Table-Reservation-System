package com.pg291.table_reservation_server.controller;

import com.pg291.table_reservation_server.model.Reservation;
import com.pg291.table_reservation_server.service.reservation.IReservationService;
import com.pg291.table_reservation_server.service.reservation.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final IReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Creates a new reservation.
     *
     * @param reservation the reservation request body
     * @return the created reservation
     */
    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.createReservation(reservation));
    }

    /**
     * Retrieves all reservations.
     *
     * @return list of all reservations
     */
    @GetMapping
    public ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    /**
     * Marks a reservation as completed and frees the associated table.
     *
     * @param id the ID of the reservation to update
     * @return success message
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable String id) {
        reservationService.markReservationAsComplete(id);
        return ResponseEntity.ok("Reservation completed!");
    }
}
