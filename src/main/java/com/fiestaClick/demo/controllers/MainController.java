package com.fiestaClick.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/catering")
    public String catering() {
        return "catering.html";
    }

    @GetMapping("/eventRoom")
    public String eventRoomController() {
        return "eventRoom.html";
    }

    @GetMapping("/extra")
    public String extraController() {
        return "extra.html";
    }

    
    
     @GetMapping("/basket")
    public String basketController() {
        return "basket.html";
    }
}
