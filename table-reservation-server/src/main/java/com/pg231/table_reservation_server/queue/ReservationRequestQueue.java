package com.pg231.table_reservation_server.queue;

import com.pg231.table_reservation_server.model.ReservationRequest;

/**
 * A custom queue implementation for ReservationRequest objects
 * using a singly linked list with front and rear pointers.
 */
public class ReservationRequestQueue {

    // Node references for the front and rear of the queue
    private Node front, rear;
    private int size;

    /**
     * Adds a new ReservationRequest to the rear of the queue.
     *
     * @param request The ReservationRequest to enqueue.
     */
    public void enqueue(ReservationRequest request) {
        Node newNode = new Node(request); // Create a new node with the request

        // If rear exists, link the current rear to the new node
        if (rear != null) {
            rear.next = newNode;
        }

        rear = newNode; // Update the rear to the new node

        // If the queue was empty, front also points to the new node
        if (front == null) {
            front = rear;
        }

        size++; // Increment the size of the queue
    }

    /**
     * Removes and returns the ReservationRequest from the front of the queue.
     *
     * @return The ReservationRequest at the front.
     * @throws IllegalStateException if the queue is empty.
     */
    public ReservationRequest dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        // Retrieve the data to return
        ReservationRequest data = front.request;

        // Move front to the next node
        front = front.next;

        // If front is now null, the queue is empty — update rear as well
        if (front == null) {
            rear = null;
        }

        size--; // Decrease size
        return data;
    }

    /**
     * Removes a ReservationRequest from the queue based on its ID.
     *
     * @param id The ID of the ReservationRequest to remove.
     * @return true if removed successfully, false if not found.
     */
    public boolean removeById(String id) {
        if (isEmpty()) return false;

        Node current = front, previous = null;

        // Traverse the list to find the node with the given ID
        while (current != null) {
            if (current.request.getId().equals(id)) {

                if (previous == null) {
                    // Case 1: Removing the front node
                    front = current.next;
                    if (front == null) rear = null; // Queue becomes empty
                } else {
                    // Case 2: Removing from middle or end
                    previous.next = current.next;
                    if (current == rear) rear = previous;
                }

                size--;
                return true;
            }

            // Move to the next node
            previous = current;
            current = current.next;
        }

        return false; // ID not found
    }

    /**
     * Returns the ReservationRequest at the front without removing it.
     *
     * @return The ReservationRequest at the front.
     * @throws IllegalStateException if the queue is empty.
     */
    public ReservationRequest peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.request;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return The size of the queue.
     */
    public int size() {
        return size;
    }
}

