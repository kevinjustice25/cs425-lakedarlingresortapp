package edu.mum.cs.cs425.lakedarlingresortapp.service;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation save(Reservation reservation, Long villaId, Long customerId);
    List<Reservation> findAll();
}
