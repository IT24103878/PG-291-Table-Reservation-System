package com.pg291.table_reservation_server.repository;

import com.pg291.table_reservation_server.model.Table;


public class TableRepository extends GenericTextFileRepository<Table> {

    private static final int FIELD_COUNT = 5;

    /**
     * Constructs a TableRepository with the file name "tables.txt".
     */
    public TableRepository() {
        super("tables.txt");
    }

    /**
     * Serializes a Table object into a string for file storage.
     *
     * @param table the table to serialize
     * @return the serialized string
     */
    @Override
    protected String serialize(Table table) {
        return String.join("|",
                table.getId(),
                String.valueOf(table.getNumber()),
                String.valueOf(table.getCapacity()),
                String.valueOf(table.isAvailable()),
                table.getWaiterId()
        );
    }

    /**
     * Deserializes a string line into a Table object.
     *
     * @param line the serialized table string
     * @return the Table object, or null if the input is invalid
     */
    @Override
    protected Table deserialize(String line) {
        String[] parts = line.split(DELIMITER);
        if (parts.length != FIELD_COUNT) {
            return null;
        }

        try {
            String id = parts[0];
            int number = Integer.parseInt(parts[1]);
            int capacity = Integer.parseInt(parts[2]);
            boolean available = Boolean.parseBoolean(parts[3]);
            String waiterId = parts[4];

            return new Table(id, number, capacity, available, waiterId);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Checks if at least one table is available.
     *
     * @return true if a table is available, false otherwise
     */
    public boolean isTableAvailable() {
        return findAll().stream().anyMatch(Table::isAvailable);
    }

    /**
     * Retrieves the ID of the first available table.
     *
     * @return the table ID if available, or null otherwise
     */
    public String getAvailableTableId() {
        return findAll().stream()
                .filter(Table::isAvailable)
                .map(Table::getId)
                .findFirst()
                .orElse(null);
    }
}
