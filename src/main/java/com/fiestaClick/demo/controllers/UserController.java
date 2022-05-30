package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.repository.UserRepository;
import com.fiestaClick.demo.services.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author conta
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String form(ModelMap modelo)  {
        return "juli";
    }
    
    @PostMapping("/login")
    public String save(ModelMap model, @RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam Date dateOfBirth)  {
        try {
            userService.save(name, email, password, dateOfBirth);
            model.put("exito", "Persona guardada con exito");
        } catch (Exception e) {
            model.put("error", "Error al registrarse");
        }
        return "juli_registro";
    }

}
