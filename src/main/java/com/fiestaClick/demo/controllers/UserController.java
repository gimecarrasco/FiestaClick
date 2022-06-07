package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.errors.ErrorService;
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
        return "login.html";
    }
    
    @PostMapping("/register")
    public String save(ModelMap model, @RequestParam String name, @RequestParam String lastName, @RequestParam Date dateOfBirth,@RequestParam String email, @RequestParam String password)  throws ErrorService{
        try {
            System.out.println("Nombre: " + name );
            System.out.println("Apellido: " + lastName );
            System.out.println("Fecha de nacimiento: " + dateOfBirth );
            System.out.println("Mail: " + email );
            System.out.println("Contraseña: " + password );
              userService.save(name,lastName, dateOfBirth, email, password);
            model.put("exito", "Has sido registrado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", "Error al registrarse");
            return "login.html";
        }
        return "index.html";
    }

}
