package edu.mum.cs.cs425.lakedarlingresortapp.controller;


import edu.mum.cs.cs425.lakedarlingresortapp.model.Address;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Customer;
import edu.mum.cs.cs425.lakedarlingresortapp.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/customers")
    public ModelAndView viewCustomers(){
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customers",customers);
        modelAndView.setViewName("customer/customers");
        return modelAndView;
    }
    @GetMapping(value = {"","/","/home"})
    public String viewHomepage(){
        return "home/index";
    }
    @GetMapping(value = "/newcustomerform")
    public String viewNewCustomerForm(Model model){
        model.addAttribute("customer",new Customer());
        model.addAttribute("address",new Address());
        return "customer/newcustomerform";
    }
    @PostMapping("/newcustomerform")
    public String saveNewCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResultCustomer,
                                  @Valid @ModelAttribute Address address, BindingResult bindingResultAddress, Model model){
        if (bindingResultAddress.hasErrors()){
            model.addAttribute("errors", bindingResultAddress.getAllErrors());
            System.out.println("address errors");
            return "customer/newcustomerform";
        } else if (bindingResultCustomer.hasErrors()){
            model.addAttribute("errors", bindingResultCustomer.getAllErrors());
            System.out.println("customer errors");
            return "customer/newcustomerform";
        }

        customerService.save(customer,address);
        return "redirect:customer/customers";
    }
}
