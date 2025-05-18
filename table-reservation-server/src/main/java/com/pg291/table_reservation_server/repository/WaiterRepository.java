package com.pg291.table_reservation_server.repository;

import com.pg291.table_reservation_server.model.Waiter;

public class WaiterRepository extends GenericTextFileRepository<Waiter> {

    private static final int FIELD_COUNT = 4;

    /**
     * Constructs a WaiterRepository with the file name "waiters.txt".
     */
    public WaiterRepository() {
        super("waiters.txt");
    }

    /**
     * Serializes a Waiter object into a string for file storage.
     *
     * @param waiter the waiter to serialize
     * @return the serialized string representation
     */
    @Override
    protected String serialize(Waiter waiter) {
        return String.join("|",
                waiter.getId(),
                waiter.getName(),
                waiter.getNic(),
                String.valueOf(waiter.isAvailable())
        );
    }

    /**
     * Deserializes a line from the file into a Waiter object.
     *
     * @param line the line to deserialize
     * @return the Waiter object or null if the format is invalid
     */
    @Override
    protected Waiter deserialize(String line) {
        String[] parts = line.split(DELIMITER);

        if (parts.length != FIELD_COUNT) {
            return null;
        }

        String id = parts[0];
        String name = parts[1];
        String nic = parts[2];
        boolean available;

        try {
            available = Boolean.parseBoolean(parts[3]);
        } catch (Exception e) {
            return null; // Graceful fallback for unexpected input
        }

        return new Waiter(id, name, nic, available);
    }
}
