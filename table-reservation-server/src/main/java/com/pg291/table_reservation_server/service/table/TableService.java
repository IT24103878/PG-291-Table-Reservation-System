package com.pg291.table_reservation_server.service.table;

import com.pg291.table_reservation_server.model.Table;
import com.pg291.table_reservation_server.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService implements ITableService {

    private final TableRepository repository;

    public TableService() {
        this.repository = new TableRepository();
    }

    @Override
    public Table create(Table table) {
        return repository.save(table);
    }

    @Override
    public List<Table> findAll() {
        return repository.findAll();
    }

    @Override
    public Table findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Table update(String id, Table updatedTable) {
        return repository.update(id, updatedTable);
    }

    @Override
    public boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public boolean isTableAvailable() {
        return repository.isTableAvailable();
    }

    @Override
    public String getAvailableTableId() {
        return repository.getAvailableTableId();
    }
}