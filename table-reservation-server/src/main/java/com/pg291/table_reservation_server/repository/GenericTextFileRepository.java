package com.pg291.table_reservation_server.repository;

import com.pg291.table_reservation_server.model.BaseEntity;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Generic repository that manages entities of type T (extending BaseEntity)
 * by storing and retrieving them from a text file.
 *
 * Subclasses must implement serialization and deserialization logic.
 */
public abstract class GenericTextFileRepository<T extends BaseEntity> {

    // Abstract methods that must be implemented by concrete subclasses
    protected abstract String serialize(T entity);     // Converts an entity to a string
    protected abstract T deserialize(String line);     // Converts a string back to an entity

    private final File file;

    protected static final String DELIMITER = "\\|";

    /**
     * Constructor initializes the repository with the specified file name.
     * Creates the file and its parent directories if they don't exist.
     */
    protected GenericTextFileRepository(String filename) {
        this.file = new File("data/" + filename);
        file.getParentFile().mkdirs();  // Ensure parent directories exist

        try {
            file.createNewFile();       // Create file if it doesn't exist
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all entities from the file.
     *
     * @return List of deserialized entities.
     */
    public List<T> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .map(this::deserialize)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds a specific entity by its unique ID.
     *
     * @param id The ID of the entity to find.
     * @return The entity if found, otherwise null.
     */
    public T findById(String id) {
        return this.findAll().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Saves a new entity by assigning a UUID and appending it to the file.
     *
     * @param entity The entity to save.
     * @return The saved entity with its assigned ID.
     */
    public T save(T entity) {
        entity.setId(UUID.randomUUID().toString());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(serialize(entity));
            writer.newLine();  // Ensure each entity is on its own line
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }

    /**
     * Updates an existing entity with the given ID.
     * Replaces the matching entry and rewrites the entire file.
     *
     * @param id The ID of the entity to update.
     * @param updatedEntity The new version of the entity.
     * @return The updated entity, or null if not found.
     */
    public T update(String id, T updatedEntity) {
        List<T> entities = findAll();
        boolean found = false;

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId().equals(id)) {
                updatedEntity.setId(id);       // Preserve the original ID
                entities.set(i, updatedEntity);
                found = true;
                break;
            }
        }

        if (found) {
            overwriteFile(entities);           // Rewrite the file with the updated list
            return updatedEntity;
        }

        return null; // Entity with given ID not found
    }

    /**
     * Deletes an entity by ID.
     *
     * @param id The ID of the entity to delete.
     * @return true if deletion was successful, false if not found.
     */
    public boolean delete(String id) {
        List<T> entities = findAll();
        boolean removed = entities.removeIf(e -> e.getId().equals(id));

        if (removed) {
            overwriteFile(entities);  // Rewrite file without the removed entity
        }

        return removed;
    }

    /**
     * Helper method to completely rewrite the file with a new list of entities.
     *
     * @param entities The list of entities to write to the file.
     */
    private void overwriteFile(List<T> entities) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (T entity : entities) {
                writer.write(serialize(entity));
                writer.newLine(); // Ensure each entity is on its own line
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
