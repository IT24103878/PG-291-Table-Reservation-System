package com.pg231.table_reservation_server.queue;

import com.pg231.table_reservation_server.model.ReservationRequest;

public class Node {
    ReservationRequest request;
    Node next;

    Node(ReservationRequest request) {
        this.request = request;
    }
}
