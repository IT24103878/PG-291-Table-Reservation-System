package com.pg231.table_reservation_server.controller;

import com.pg231.table_reservation_server.model.Table;
import com.pg231.table_reservation_server.service.table.ITableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    private final ITableService tableService;

    public TableController(ITableService tableService) {
        this.tableService = tableService;
    }

    /**
     * Create a new table.
     */
    @PostMapping
    public ResponseEntity<Table> createTable(@RequestBody Table table) {
        Table created = tableService.create(table);
        return ResponseEntity.ok(created);
    }

    /**
     * Get all tables.
     */
    @GetMapping
    public ResponseEntity<List<Table>> getAllTables() {
        return ResponseEntity.ok(tableService.findAll());
    }

    /**
     * Get a table by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Table> getTableById(@PathVariable String id) {
        Table table = tableService.findById(id);
        return (table != null)
                ? ResponseEntity.ok(table)
                : ResponseEntity.notFound().build();
    }

    /**
     * Update a table by ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Table> updateTable(@PathVariable String id, @RequestBody Table updatedTable) {
        Table updated = tableService.update(id, updatedTable);
        return (updated != null)
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    /**
     * Delete a table by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable String id) {
        boolean deleted = tableService.delete(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * Check if any table is available.
     */
    @GetMapping("/available")
    public ResponseEntity<Boolean> isTableAvailable() {
        return ResponseEntity.ok(tableService.isTableAvailable());
    }

    /**
     * Get the ID of an available table.
     */
    @GetMapping("/available/id")
    public ResponseEntity<String> getAvailableTableId() {
        String availableId = tableService.getAvailableTableId();
        return (availableId != null)
                ? ResponseEntity.ok(availableId)
                : ResponseEntity.notFound().build();
    }
}
