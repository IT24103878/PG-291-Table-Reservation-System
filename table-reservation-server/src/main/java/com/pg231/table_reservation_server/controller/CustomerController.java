package com.pg231.table_reservation_server.controller;

import com.pg231.table_reservation_server.model.Customer;
import com.pg231.table_reservation_server.service.customer.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Creates a new customer.
     *
     * @param customer the customer object to create
     * @return the created customer
     */
    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }

    /**
     * Retrieves all customers.
     *
     * @return list of all customers
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id the ID of the customer
     * @return the customer object if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable String id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    /**
     * Updates a customer with the specified ID.
     *
     * @param id the ID of the customer to update
     * @param updatedCustomer the updated customer data
     * @return the updated customer object or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable String id, @RequestBody Customer updatedCustomer) {
        Customer updated = customerService.update(id, updatedCustomer);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a customer by ID.
     *
     * @param id the ID of the customer to delete
     * @return 204 No Content if deleted, or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return customerService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
