package edu.mum.cs.cs425.lakedarlingresortapp.controller;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Address;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Owner;
import edu.mum.cs.cs425.lakedarlingresortapp.service.impl.AddressService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.impl.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
public class OwnerController {

    @Autowired
    private OwnerService ownerService;


    @GetMapping(value = "/newownerform")
    public ModelAndView viewOwnerForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("owner", new Owner());
        modelAndView.addObject("address", new Address());
        modelAndView.setViewName("owner/newownerform");
        return modelAndView;
    }
    @PostMapping(value = "/newownerform")
    public String saveNewOwner(@Valid @ModelAttribute Owner owner, BindingResult bindingResultOwner,
                               @Valid @ModelAttribute Address address, BindingResult bindingResultAddress,
                               Model model){
        if (bindingResultAddress.hasErrors()){
            model.addAttribute("errors", bindingResultAddress.getAllErrors());
            return "owner/newownerform";
        } else if (bindingResultOwner.hasErrors()){
            model.addAttribute("errors", bindingResultOwner.getAllErrors());
            return "owner/newownerform";
        }
        ownerService.save(owner,address);
        return "redirect:/owners";
    }
    @GetMapping("/owners")
    public ModelAndView viewOwners(){
        ModelAndView modelAndView = new ModelAndView();
        List<Owner> owners = ownerService.findAll();
        modelAndView.addObject("owners", owners);
        modelAndView.setViewName("owner/owners");
        return modelAndView;
    }
}
