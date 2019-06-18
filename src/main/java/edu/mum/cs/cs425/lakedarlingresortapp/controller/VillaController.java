package edu.mum.cs.cs425.lakedarlingresortapp.controller;


import edu.mum.cs.cs425.lakedarlingresortapp.model.Owner;
import edu.mum.cs.cs425.lakedarlingresortapp.model.Villa;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IOwnerService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IVillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class VillaController {

    @Autowired
    IVillaService villaService;

    @Autowired
    IOwnerService ownerService;

    @GetMapping("/villas")
    public ModelAndView viewVillas(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("villas", villaService.findAll());
        modelAndView.setViewName("villa/villas");
        return modelAndView;
    }
    @GetMapping("/newvillaform")
    public ModelAndView viewNewVillaForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("villa", new Villa());
        modelAndView.addObject("owners", ownerService.findAll());
        modelAndView.setViewName("villa/newvillaform");
        return modelAndView;
    }
    @PostMapping("/newvillaform")
    public String saveNewVilla(@Valid @ModelAttribute Villa villa, BindingResult bindingResultVilla,
                               @RequestParam Long ownerid, Model model){
        if (bindingResultVilla.hasErrors()){
            model.addAttribute("errors",bindingResultVilla.getAllErrors());
            bindingResultVilla.getAllErrors().stream().forEach(
                    (objectError -> System.out.println(objectError.toString())));
            return "villa/newvillaform";
        }

        villaService.save(villa, ownerid);
        return "redirect:/villas";
    }
}
