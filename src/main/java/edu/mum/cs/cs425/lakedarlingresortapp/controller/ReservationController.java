package edu.mum.cs.cs425.lakedarlingresortapp.controller;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Reservation;
import edu.mum.cs.cs425.lakedarlingresortapp.service.ICustomerService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IReservationService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IVillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
        modelAndView.addObject("customers", customerService.findAll());
        modelAndView.addObject("villas",villaService.findAll());
        modelAndView.addObject("reservation",new Reservation());
        modelAndView.setViewName("reservation/newreservationform");
        return modelAndView;
    }
    @PostMapping("/newreservationform")
    public ModelAndView saveRegistrationForm(@Valid @ModelAttribute Reservation reservation, BindingResult bindingResult,
                                       @RequestParam Long villaid, @RequestParam Long customerid){
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach(objectError -> System.out.println(objectError.toString()));
            modelAndView.getModel().put("errors",bindingResult.getAllErrors());
            modelAndView.addObject("villas",villaService.findAll());
            modelAndView.addObject("customers", customerService.findAll());
            modelAndView.setViewName("reservation/newreservationform");
            return modelAndView;
        }
        reservationService.save(reservation,villaid,customerid);
        modelAndView.setViewName("home/index");
        return modelAndView;
    }
}
