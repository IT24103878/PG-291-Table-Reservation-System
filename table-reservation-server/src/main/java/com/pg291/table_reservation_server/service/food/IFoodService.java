package com.pg291.table_reservation_server.service.food;

import com.pg291.table_reservation_server.model.Food;

import java.util.List;

/**
 * Interface defining food service operations.
 */
public interface IFoodService {

    /**
     * Creates a new food item.
     *
     * @param food the food item to be created
     * @return the created food item
     */
    Food create(Food food);

    /**
     * Retrieves all food items.
     *
     * @return list of all food items
     */
    List<Food> findAll();

    /**
     * Retrieves a food item by ID.
     *
     * @param id the food item ID
     * @return the matching food item, or null if not found
     */
    Food findById(String id);

    /**
     * Updates an existing food item.
     *
     * @param id the ID of the food item to update
     * @param updatedFood the new food data
     * @return the updated food item, or null if not found
     */
    Food update(String id, Food updatedFood);

    /**
     * Deletes a food item by ID.
     *
     * @param id the food item ID
     * @return true if the item was deleted, false otherwise
     */
    boolean delete(String id);
}

