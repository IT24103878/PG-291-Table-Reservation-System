package com.pg231.table_reservation_server.repository;

import com.pg231.table_reservation_server.model.ReservationRequest;

import java.time.LocalDateTime;

public class ReservationRequestRepository extends GenericTextFileRepository<ReservationRequest> {

    private static final int FIELD_COUNT = 4;

    /**
     * Constructs a ReservationRequestRepository with the file name "ReservationRequests.txt".
     */
    public ReservationRequestRepository() {
        super("ReservationRequests.txt");
    }

    /**
     * Serializes a ReservationRequest object into a string for storage.
     *
     * @param request the reservation request to serialize
     * @return the serialized string
     */
    @Override
    protected String serialize(ReservationRequest request) {
        return String.join("|",
                request.getId(),
                request.getCustomerId(),
                request.getDateTime().toString(),
                String.valueOf(request.getCount())
        );
    }

    /**
     * Deserializes a string line into a ReservationRequest object.
     *
     * @param line the serialized string
     * @return the deserialized ReservationRequest object or null if invalid
     */
    @Override
    protected ReservationRequest deserialize(String line) {
        String[] parts = line.split(DELIMITER);
        if (parts.length != FIELD_COUNT) {
            return null;
        }

        try {
            String id = parts[0];
            String customerId = parts[1];
            LocalDateTime dateTime = LocalDateTime.parse(parts[2]);
            int count = Integer.parseInt(parts[3]);

            return new ReservationRequest(id, customerId, dateTime, count);
        } catch (Exception e) {
            return null;
        }
    }
}
