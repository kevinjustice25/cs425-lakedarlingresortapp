package edu.mum.cs.cs425.lakedarlingresortapp.repository;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
