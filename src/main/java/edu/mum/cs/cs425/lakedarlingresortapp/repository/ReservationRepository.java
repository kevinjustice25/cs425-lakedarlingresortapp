package edu.mum.cs.cs425.lakedarlingresortapp.repository;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("SELECT r from Reservation r where ?1 between r.startDate AND r.endDate or " +
            "?2 between r.startDate AND r.endDate or r.startDate between ?1 and ?2 or " +
            "r.endDate between ?1 AND ?2")
    List<Reservation> availableReservations(LocalDate startdate, LocalDate enddate);
}
