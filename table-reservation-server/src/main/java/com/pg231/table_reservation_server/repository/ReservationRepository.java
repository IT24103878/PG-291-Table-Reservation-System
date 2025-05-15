package com.pg231.table_reservation_server.repository;

import com.pg231.table_reservation_server.model.OrderItem;
import com.pg231.table_reservation_server.model.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationRepository extends GenericTextFileRepository<Reservation> {

    private static final String FILE_NAME = "Reservations.txt";
    private static final String ITEM_DELIMITER = ";";
    private static final int EXPECTED_FIELDS = 7;

    public ReservationRepository() {
        super(FILE_NAME);
    }

    @Override
    protected String serialize(Reservation reservation) {
        String itemsStr = reservation.getItems().stream()
                .map(OrderItem::toString)
                .collect(Collectors.joining(ITEM_DELIMITER));

        return String.join("|",
                reservation.getId(),
                reservation.getCustomerId(),
                reservation.getDateTime().toString(),
                itemsStr,
                reservation.getTableId(),
                String.valueOf(reservation.getTotalPrice()),
                String.valueOf(reservation.isCompleted())
        );
    }

    @Override
    protected Reservation deserialize(String line) {
        String[] parts = line.split(DELIMITER, -1);

        if (parts.length != EXPECTED_FIELDS) {
            return null; // or throw custom exception for logging
        }

        try {
            String id = parts[0];
            String customerId = parts[1];
            LocalDateTime dateTime = LocalDateTime.parse(parts[2]);
            List<OrderItem> items = parseOrderItems(parts[3]);
            String tableId = parts[4];
            double totalPrice = Double.parseDouble(parts[5]);
            boolean isCompleted = Boolean.parseBoolean(parts[6]);

            return new Reservation(id, customerId, dateTime, items, tableId, totalPrice, isCompleted);
        } catch (Exception e) {
            // log the error if desired
            return null;
        }
    }

    private List<OrderItem> parseOrderItems(String itemsStr) {
        List<OrderItem> items = new ArrayList<>();
        if (itemsStr == null || itemsStr.isEmpty()) {
            return items;
        }

        for (String itemStr : itemsStr.split(ITEM_DELIMITER)) {
            items.add(OrderItem.fromString(itemStr));
        }
        return items;
    }
}

