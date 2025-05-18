package com.pg291.table_reservation_server.service.table;

import com.pg291.table_reservation_server.model.Table;

import java.util.List;

/**
 * Interface defining table service operations.
 */
public interface ITableService {

    /**
     * Creates a new table.
     *
     * @param table the table to be created
     * @return the created table
     */
    Table create(Table table);

    /**
     * Retrieves all tables.
     *
     * @return list of all tables
     */
    List<Table> findAll();

    /**
     * Retrieves a table by ID.
     *
     * @param id the table ID
     * @return the matching table, or null if not found
     */
    Table findById(String id);

    /**
     * Updates an existing table.
     *
     * @param id the ID of the table to update
     * @param updatedTable the new table data
     * @return the updated table, or null if not found
     */
    Table update(String id, Table updatedTable);

    /**
     * Deletes a table by ID.
     *
     * @param id the table ID
     * @return true if the table was deleted, false otherwise
     */
    boolean delete(String id);

    /**
     * Checks if any table is available.
     *
     * @return true if there is an available table, false otherwise
     */
    boolean isTableAvailable();

    /**
     * Retrieves the ID of an available table.
     *
     * @return the ID of an available table
     */
    String getAvailableTableId();
}
