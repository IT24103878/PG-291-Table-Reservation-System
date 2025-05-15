package com.pg231.table_reservation_server.service.reservation;

import com.pg231.table_reservation_server.merge_sort.MergeSortReservation;
import com.pg231.table_reservation_server.model.Reservation;
import com.pg231.table_reservation_server.model.Table;
import com.pg231.table_reservation_server.repository.ReservationRepository;
import com.pg231.table_reservation_server.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final TableRepository tableRepository;

    public ReservationService() {
        this.reservationRepository = new ReservationRepository();
        this.tableRepository = new TableRepository();
    }

    /**
     * Creates a new reservation and marks the associated table as unavailable.
     *
     * @param reservation the reservation to be created
     * @return the created reservation
     */
    @Override
    public Reservation createReservation(Reservation reservation) {
        // Save the reservation
        Reservation createdReservation = reservationRepository.save(reservation);

        // Mark the reserved table as unavailable
        Table table = tableRepository.findById(createdReservation.getTableId());
        table.setAvailable(false);
        tableRepository.update(table.getId(), table);

        return createdReservation;
    }

    /**
     * Marks a reservation as completed and sets the associated table as available again.
     *
     * @param id the ID of the reservation to complete
     */
    @Override
    public void markReservationAsComplete(String id) {
        // Fetch and update reservation status
        Reservation reservation = reservationRepository.findById(id);
        reservation.setCompleted(true);
        reservationRepository.update(id, reservation);

        // Mark the table as available
        Table table = tableRepository.findById(reservation.getTableId());
        table.setAvailable(true);
        tableRepository.update(table.getId(), table);
    }

    /**
     * Retrieves all reservations sorted using merge sort.
     *
     * @return a list of sorted reservations
     */
    @Override
    public List<Reservation> findAll() {
        return MergeSortReservation.sort(reservationRepository.findAll());
    }
}
