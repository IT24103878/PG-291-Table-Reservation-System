package com.pg231.table_reservation_server.merge_sort;

import com.pg231.table_reservation_server.model.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * MergeSortReservation provides a static method to sort a list of Reservation
 * objects using the merge sort algorithm, based on the dateTime attribute.
 */
public class MergeSortReservation {

    /**
     * Public method to sort a list of reservations by their dateTime.
     *
     * @param reservations The list of Reservation objects to be sorted.
     * @return A new sorted list of Reservation objects.
     */
    public static List<Reservation> sort(List<Reservation> reservations) {
        // Base case: if the list is null or has one element, it's already sorted
        if (reservations == null || reservations.size() <= 1) {
            return reservations;
        }

        // Divide: find the middle index and split the list into two halves
        int mid = reservations.size() / 2;

        // Recursively sort both halves
        List<Reservation> left = sort(new ArrayList<>(reservations.subList(0, mid)));
        List<Reservation> right = sort(new ArrayList<>(reservations.subList(mid, reservations.size())));

        // Conquer: merge the sorted halves
        return merge(left, right);
    }

    /**
     * Helper method to merge two sorted lists of Reservation objects.
     *
     * @param left  The left sorted list.
     * @param right The right sorted list.
     * @return A merged and sorted list containing all elements from left and right.
     */
    private static List<Reservation> merge(List<Reservation> left, List<Reservation> right) {
        List<Reservation> result = new ArrayList<>();

        int i = 0, j = 0;

        // Merge elements from both lists in order based on dateTime
        while (i < left.size() && j < right.size()) {
            LocalDateTime leftTime = left.get(i).getDateTime();
            LocalDateTime rightTime = right.get(j).getDateTime();

            // Compare dateTime values; add the earlier one to the result
            if (leftTime.isBefore(rightTime) || leftTime.isEqual(rightTime)) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        // Append remaining elements from left list (if any)
        while (i < left.size()) {
            result.add(left.get(i++));
        }

        // Append remaining elements from right list (if any)
        while (j < right.size()) {
            result.add(right.get(j++));
        }

        return result;
    }
}


