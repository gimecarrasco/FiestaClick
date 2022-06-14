package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.UserRepository;
import com.fiestaClick.demo.service.UserService;
import com.sun.istack.logging.Logger;
import java.util.Date;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String form(ModelMap modelo)  {
        return "login.html";
    }
    
    @PostMapping("/register")
    public String save(ModelMap model, @RequestParam String name, @RequestParam String lastName, @RequestParam Date dateOfBirth,@RequestParam String email, @RequestParam String password)  throws ErrorService{
        try {
            userService.save(name,lastName, dateOfBirth, email, password);
            model.put("exito", "Felicitaciones!");            
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", e.getMessage());
            model.put("name", name);
            model.put("lastName", lastName);
            model.put("dateOfBirth", dateOfBirth);
            model.put("email", email);
            model.put("password", password);
            //Logger.getLogger(MainController.class.getName().log(Level.SEVERE, null, e));
            return "login.html";
        }
        return "login.html";
    }

}
