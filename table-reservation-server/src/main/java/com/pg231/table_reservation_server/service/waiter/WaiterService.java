package com.pg231.table_reservation_server.service.waiter;

import com.pg231.table_reservation_server.model.Waiter;
import com.pg231.table_reservation_server.repository.WaiterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaiterService implements IWaiterService {

    private final WaiterRepository repository;

    public WaiterService() {
        this.repository = new WaiterRepository();
    }

    @Override
    public Waiter create(Waiter waiter) {
        return repository.save(waiter);
    }

    @Override
    public List<Waiter> findAll() {
        return repository.findAll();
    }

    @Override
    public Waiter findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Waiter update(String id, Waiter updatedWaiter) {
        return repository.update(id, updatedWaiter);
    }

    @Override
    public boolean delete(String id) {
        return repository.delete(id);
    }
}
