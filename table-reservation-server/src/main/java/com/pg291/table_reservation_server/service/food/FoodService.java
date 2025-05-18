package com.pg291.table_reservation_server.service.food;

import com.pg291.table_reservation_server.model.Food;
import com.pg291.table_reservation_server.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements IFoodService {

    private final FoodRepository repository;

    public FoodService() {
        this.repository = new FoodRepository();
    }

    @Override
    public Food create(Food food) {
        return repository.save(food);
    }

    @Override
    public List<Food> findAll() {
        return repository.findAll();
    }

    @Override
    public Food findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Food update(String id, Food updatedFood) {
        return repository.update(id, updatedFood);
    }

    @Override
    public boolean delete(String id) {
        return repository.delete(id);
    }
}