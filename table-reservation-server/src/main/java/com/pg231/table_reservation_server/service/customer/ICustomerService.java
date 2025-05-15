package com.pg231.table_reservation_server.service.customer;

import com.pg231.table_reservation_server.model.Customer;

import java.util.List;

/**
 * Service interface for managing customer operations.
 */
public interface ICustomerService {
    /**
     * Creates a new customer.
     *
     * @param customer the customer to create
     * @return the created customer
     */
    Customer create(Customer customer);

    /**
     * Retrieves all customers.
     *
     * @return list of customers
     */
    List<Customer> findAll();

    /**
     * Finds a customer by ID.
     *
     * @param id the customer ID
     * @return the customer, or null if not found
     */
    Customer findById(String id);

    /**
     * Updates an existing customer.
     *
     * @param id the customer ID
     * @param updatedCustomer the customer with updated values
     * @return the updated customer, or null if not found
     */
    Customer update(String id, Customer updatedCustomer);

    /**
     * Deletes a customer by ID.
     *
     * @param id the customer ID
     * @return true if deleted, false if not found
     */
    boolean delete(String id);
}
