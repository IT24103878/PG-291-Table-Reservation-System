package com.pg291.table_reservation_server.controller;

import com.pg291.table_reservation_server.model.Food;
import com.pg291.table_reservation_server.service.food.IFoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final IFoodService foodService;

    public FoodController(IFoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * Create a new food item.
     */
    @PostMapping
    public ResponseEntity<Food> create(@RequestBody Food food) {
        Food created = foodService.create(food);
        return ResponseEntity.ok(created);
    }

    /**
     * Get all food items.
     */
    @GetMapping
    public ResponseEntity<List<Food>> getAll() {
        return ResponseEntity.ok(foodService.findAll());
    }

    /**
     * Get a food item by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Food> getById(@PathVariable String id) {
        Food food = foodService.findById(id);
        if (food == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(food);
    }

    /**
     * Update a food item by ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Food> update(@PathVariable String id, @RequestBody Food updatedFood) {
        Food updated = foodService.update(id, updatedFood);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete a food item by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = foodService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}