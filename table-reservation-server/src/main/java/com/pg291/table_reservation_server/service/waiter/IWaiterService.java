package com.pg291.table_reservation_server.service.waiter;

import com.pg291.table_reservation_server.model.Waiter;

import java.util.List;

/**
 * Interface defining waiter service operations.
 */
public interface IWaiterService {

    /**
     * Creates a new waiter.
     *
     * @param waiter the waiter to be created
     * @return the created waiter
     */
    Waiter create(Waiter waiter);

    /**
     * Retrieves all waiters.
     *
     * @return list of all waiters
     */
    List<Waiter> findAll();

    /**
     * Retrieves a waiter by ID.
     *
     * @param id the ID of the waiter
     * @return the waiter if found, otherwise null
     */
    Waiter findById(String id);

    /**
     * Updates an existing waiter.
     *
     * @param id the ID of the waiter to update
     * @param updatedWaiter the updated waiter object
     * @return the updated waiter if found, otherwise null
     */
    Waiter update(String id, Waiter updatedWaiter);

    /**
     * Deletes a waiter by ID.
     *
     * @param id the ID of the waiter to delete
     * @return true if the waiter was deleted, false otherwise
     */
    boolean delete(String id);
}

