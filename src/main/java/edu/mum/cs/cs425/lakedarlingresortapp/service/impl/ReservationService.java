package edu.mum.cs.cs425.lakedarlingresortapp.service.impl;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Reservation;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Villa;
import edu.mum.cs.cs425.lakedarlingresortapp.repository.ReservationRepository;
import edu.mum.cs.cs425.lakedarlingresortapp.service.ICustomerService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IReservationService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IVillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        System.out.println(reservation.getVilla().toString() + "\n" + reservation.getCustomer().toString() + "\n" +
                reservation.getStartDate().toString() + reservation.getEndDate().toString() + reservation.getTotalPrice()
                + "\n" + reservation.getReservationId());
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Set<Villa> availableReservations(LocalDate startdate, LocalDate enddate, Integer numBeds) {
        List<Reservation> reservations = reservationRepository.availableReservations(startdate,enddate);
        Set<Villa> bookedVillas = reservations.stream().map(reservation -> reservation.getVilla())
                .collect(Collectors.toSet());
        Set<Villa> availableVillas = villaService.findAll().stream().collect(Collectors.toSet());
        for (Villa bookedvilla : bookedVillas) {
            availableVillas.remove(bookedvilla);
        }
        availableVillas = availableVillas.stream().filter(villa -> villa.getNumberBeds().intValue() == numBeds.intValue())
                .collect(Collectors.toSet());
        return availableVillas;
    }
}
