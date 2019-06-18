package edu.mum.cs.cs425.lakedarlingresortapp.service.impl;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Reservation;
import edu.mum.cs.cs425.lakedarlingresortapp.repository.ReservationRepository;
import edu.mum.cs.cs425.lakedarlingresortapp.service.ICustomerService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IReservationService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IVillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reservationService")
public class ReservationService implements IReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    IVillaService villaService;

    @Autowired
    ICustomerService customerService;

    @Override
    public Reservation save(Reservation reservation, Long villaId, Long customerId) {
        reservation.setCustomer(customerService.findById(customerId));
        reservation.setVilla(villaService.findById(villaId));
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
