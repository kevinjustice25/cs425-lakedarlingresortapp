package edu.mum.cs.cs425.lakedarlingresortapp.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HomeController {

    @GetMapping(value = {"","/","/home"})
    public String viewHomepage(){
        return "home/index";
    }

    @GetMapping(value = "/login")
    public String viewLoginPage(){
        return "home/login";
    }

    @PostMapping(value = "/login")
    public String processLogin(@RequestParam Map<String,String> requestParams){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = requestParams.get("username");
        String password = requestParams.get("password");
        System.out.println(username + "   " + password);
        System.out.println(passwordEncoder.encode(password));

        return "/home";
    }
}
