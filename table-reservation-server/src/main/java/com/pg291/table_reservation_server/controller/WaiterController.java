package com.pg291.table_reservation_server.controller;

import com.pg291.table_reservation_server.model.Waiter;
import com.pg291.table_reservation_server.service.waiter.IWaiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waiters")
public class WaiterController {

    private final IWaiterService waiterService;

    public WaiterController(IWaiterService waiterService) {
        this.waiterService = waiterService;
    }

    /**
     * Creates a new waiter.
     *
     * @param waiter the waiter to create
     * @return the created waiter
     */
    @PostMapping
    public ResponseEntity<Waiter> create(@RequestBody Waiter waiter) {
        return ResponseEntity.ok(waiterService.create(waiter));
    }

    /**
     * Retrieves all waiters.
     *
     * @return list of all waiters
     */
    @GetMapping
    public ResponseEntity<List<Waiter>> getAll() {
        return ResponseEntity.ok(waiterService.findAll());
    }

    /**
     * Retrieves a waiter by ID.
     *
     * @param id the ID of the waiter
     * @return the waiter if found, otherwise 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Waiter> getById(@PathVariable String id) {
        Waiter waiter = waiterService.findById(id);
        return waiter != null
                ? ResponseEntity.ok(waiter)
                : ResponseEntity.notFound().build();
    }

    /**
     * Updates a waiter by ID.
     *
     * @param id the ID of the waiter to update
     * @param updatedWaiter the updated waiter object
     * @return the updated waiter if found, otherwise 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Waiter> update(@PathVariable String id, @RequestBody Waiter updatedWaiter) {
        Waiter updated = waiterService.update(id, updatedWaiter);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a waiter by ID.
     *
     * @param id the ID of the waiter to delete
     * @return 204 No Content if deleted, 404 Not Found if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return waiterService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
