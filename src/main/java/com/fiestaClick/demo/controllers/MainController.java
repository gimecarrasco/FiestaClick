package com.fiestaClick.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/index")
    public String index(ModelMap modelo) {
        modelo.put("exito", "Has sido registrado exitosamente.");
        return "index.html";
    } 
    
     @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model) {
         if (error != null) {
             model.put("error", "El nombre de usuario o contraseña son incorrectos.");
         }
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
