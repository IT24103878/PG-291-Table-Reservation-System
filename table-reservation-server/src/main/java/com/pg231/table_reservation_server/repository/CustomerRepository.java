package com.pg231.table_reservation_server.repository;

import com.pg231.table_reservation_server.model.Customer;


public class CustomerRepository extends GenericTextFileRepository<Customer> {

    private static final int FIELD_COUNT = 3;

    /**
     * Constructs a CustomerRepository with the file name "customers.txt".
     */
    public CustomerRepository() {
        super("customers.txt");
    }

    /**
     * Serializes a Customer object into a string for file storage.
     *
     * @param customer the customer to serialize
     * @return the serialized string representation
     */
    @Override
    protected String serialize(Customer customer) {
        return String.join("|",
                customer.getId(),
                customer.getName(),
                customer.getPhone()
        );
    }

    /**
     * Deserializes a line from the file into a Customer object.
     *
     * @param line the line to deserialize
     * @return the Customer object or null if the format is invalid
     */
    @Override
    protected Customer deserialize(String line) {
        String[] parts = line.split(DELIMITER);

        if (parts.length != FIELD_COUNT) {
            return null;
        }

        String id = parts[0];
        String name = parts[1];
        String phone = parts[2];

        return new Customer(id, name, phone);
    }
}
