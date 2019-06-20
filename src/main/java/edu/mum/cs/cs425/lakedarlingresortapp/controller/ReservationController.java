package edu.mum.cs.cs425.lakedarlingresortapp.controller;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Customer;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Reservation;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Villa;
import edu.mum.cs.cs425.lakedarlingresortapp.service.ICustomerService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IReservationService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IVillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ReservationController {

    @Autowired
    IReservationService reservationService;

    @Autowired
    ICustomerService customerService;

    @Autowired
    IVillaService villaService;

    @GetMapping("/reservations")
    public ModelAndView viewReservations(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reservations", reservationService.findAll());
        modelAndView.setViewName("reservation/reservations");
        return modelAndView;
    }
    @GetMapping("/newreservationform")
    public ModelAndView viewNewRegistrationForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reservation/newreservationform");
        return modelAndView;
    }

    @PostMapping("/booknow")
    public ModelAndView selectVillaPrepareCheckout( @RequestParam String startdate,
                                            @RequestParam String enddate, @RequestParam String numbeds){
        ModelAndView modelAndView = new ModelAndView();
        LocalDate startDate = LocalDate.parse(enddate);
        LocalDate endDate = LocalDate.parse(startdate);
        if (isValidDates(startDate,endDate)){
            modelAndView.addObject("errors", "Invalid Dates");
            modelAndView.setViewName("reservation/newreservationform");
            return modelAndView;
        }
        Set<Villa> villas = reservationService.availableReservations(startDate,endDate, Integer.parseInt(numbeds));
        if (villas.size()==0){
            modelAndView.addObject("errors", "Sorry No Matching Villas Found");
            modelAndView.setViewName("reservation/newreservationform");
            return modelAndView;
        }
        Villa villa = villaPicker(villas);
        Float totalPrice = calcPrice(startDate,endDate,villa.getPrice());
        modelAndView.addObject("villa",villa);
        modelAndView.addObject("startdate",endDate);
        modelAndView.addObject("enddate",startDate);
        modelAndView.addObject("totalprice", totalPrice);
        modelAndView.setViewName("reservation/checkoutform");
        return modelAndView;
    }
    @PostMapping("/checkout")
    @ResponseBody
    public ModelAndView checkoutVilla(@RequestParam String villaid, @RequestParam String startdate,
                                      @RequestParam String enddate, Principal principal,
                                    @RequestParam String totalprice){
        LocalDate startDate = LocalDate.parse(startdate);
        LocalDate endDate = LocalDate.parse(enddate);
        Customer customer = getCustomer(principal);
        Reservation reservation = new Reservation();
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setTotalPrice(priceToFloat(totalprice));
        reservationService.save(reservation,Long.parseLong(villaid),customer.getCustomerId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reservation", reservation);
        modelAndView.setViewName("reservation/success");
        return modelAndView;
    }
    @GetMapping("/viewvillas")
    public ModelAndView viewavAvailableVillasForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("villa/checkvillas");
        return modelAndView;
    }

    @PostMapping("/viewvillas")
        public ModelAndView viewAvailableVillas(@RequestParam String enddate,
                                            @RequestParam String startdate, ModelAndView modelAndView){
        LocalDate startDate = LocalDate.parse(enddate);
        LocalDate endDate = LocalDate.parse(startdate);
        if (isValidDates(startDate,endDate)){
            modelAndView.addObject("errors", "Invalid Dates");
            modelAndView.setViewName("villa/checkvillas");
            return modelAndView;
        }
        Set<Villa> villas = reservationService.availableReservations(startDate,endDate);
        if (villas.size()==0){
            modelAndView.addObject("errors", "Sorry No Matching Villas Found");
            modelAndView.setViewName("villa/checkvillas");
            return modelAndView;
        }
        modelAndView.addObject("villas",villas);
        modelAndView.setViewName("villa/availablevillas");
        return modelAndView;
    }

    private Customer getCustomer(Principal principal){
        return customerService.findAll().stream()
                .filter(cust -> cust.getEmail().equalsIgnoreCase(principal.getName()))
                .collect(Collectors.toList()).get(0);
    }
    private Float priceToFloat(String price){
        price = price.substring(1,price.length()-1);
        price = price.replace(",", "");
        return Float.parseFloat(price);
    }
    private boolean isValidDates(LocalDate start, LocalDate end){
        return !end.isEqual(start) && !end.isBefore(start);
    }

    private Villa villaPicker(Set<Villa> villas){
        Villa villa;
        if (villas.size() == 0){ return null; }
        if (villas.size() == 1){ return villas.iterator().next(); }
        int randomIndex = (int)Math.floor(Math.random()*villas.size());
        villa = (Villa)villas.toArray()[randomIndex];
        return villa;
    }

    private Float calcPrice(LocalDate startDate, LocalDate endDate, Float pricePerNight){
        int nights = startDate.getDayOfYear() - endDate.getDayOfYear();
        return pricePerNight * nights;
    }

}
