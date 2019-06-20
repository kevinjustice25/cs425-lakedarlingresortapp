package edu.mum.cs.cs425.lakedarlingresortapp.service;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Reservation;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Villa;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IReservationService {
    Set<Villa> availableReservations(LocalDate startdate, LocalDate enddate, Integer numBeds);
    Set<Villa> availableReservations(LocalDate startdate, LocalDate enddate);
    Reservation save(Reservation reservation, Long villaId, Long customerId);
    List<Reservation> findAll();
}
