package edu.mum.cs.cs425.lakedarlingresortapp.controller;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Customer;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Reservation;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Villa;
import edu.mum.cs.cs425.lakedarlingresortapp.service.ICustomerService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IReservationService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IVillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    @ResponseBody
    public ModelAndView viewAvailableVillas(@RequestParam String numbeds, @RequestParam String startdate,
                                            @RequestParam String enddate, Principal principal, Authentication authentication){
        System.out.println(principal.toString());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getAuthorities());
        ModelAndView modelAndView = new ModelAndView();
        LocalDate startDate = LocalDate.parse(startdate);
        LocalDate endDate = LocalDate.parse(enddate);
        Set<Villa> villas = reservationService.availableReservations(startDate,endDate, Integer.parseInt(numbeds));
        if (villas.size()==0){
            modelAndView.addObject("errors", "Sorry No Matching Villas Found");
            modelAndView.setViewName("reservation/newreservationform");
        }
        Villa villa = villaPicker(villas);
        Float totalPrice = calcPrice(startDate,endDate,villa.getPrice());
        modelAndView.addObject("villa",villa);
        modelAndView.addObject("startdate",startDate);
        modelAndView.addObject("enddate",endDate);
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
        Customer customer = customerService.findAll().stream()
                .filter(cust -> cust.getEmail().equalsIgnoreCase(principal.getName()))
                .collect(Collectors.toList()).get(0);
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

    private Float priceToFloat(String price){
        price = price.substring(1,price.length()-1);
        price = price.replace(",", "");
        return Float.parseFloat(price);
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
        int nights = endDate.getDayOfYear()- startDate.getDayOfYear();
        return pricePerNight * nights;
    }

}
