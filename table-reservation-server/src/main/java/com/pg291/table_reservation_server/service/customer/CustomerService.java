package com.pg291.table_reservation_server.service.customer;

import com.pg291.table_reservation_server.model.Customer;
import com.pg291.table_reservation_server.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository repository;

    public CustomerService() {
        this.repository = new CustomerRepository();
    }

    @Override
    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Customer update(String id, Customer updatedCustomer) {
        return repository.update(id, updatedCustomer);
    }

    @Override
    public boolean delete(String id) {
        return repository.delete(id);
    }
}