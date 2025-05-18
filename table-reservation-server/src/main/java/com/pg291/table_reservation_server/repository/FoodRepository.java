package com.pg291.table_reservation_server.repository;

import com.pg291.table_reservation_server.model.Food;

public class FoodRepository extends GenericTextFileRepository<Food> {

    private static final int FIELD_COUNT = 5;

    /**
     * Constructs a FoodRepository with the file name "foods.txt".
     */
    public FoodRepository() {
        super("foods.txt");
    }

    /**
     * Serializes a Food object into a string for file storage.
     *
     * @param food the food item to serialize
     * @return the serialized string representation
     */
    @Override
    protected String serialize(Food food) {
        return String.join("|",
                food.getId(),
                food.getName(),
                food.getImageUrl(),
                food.getDescription(),
                String.valueOf(food.getPrice())
        );
    }

    /**
     * Deserializes a line from the file into a Food object.
     *
     * @param line the line to deserialize
     * @return the Food object or null if the format is invalid
     */
    @Override
    protected Food deserialize(String line) {
        String[] parts = line.split(DELIMITER);

        if (parts.length != FIELD_COUNT) {
            return null;
        }

        String id = parts[0];
        String name = parts[1];
        String imageUrl = parts[2];
        String description = parts[3];
        double price;

        try {
            price = Double.parseDouble(parts[4]);
        } catch (NumberFormatException e) {
            return null; // handle malformed price gracefully
        }

        return new Food(id, name, imageUrl, description, price);
    }
}
