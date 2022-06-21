package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.UserEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.service.PartyService;
import com.sun.istack.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {
    
     @Autowired
    private PartyService partyService;

    @GetMapping("/index")
    public String index(ModelMap modelo) {
        modelo.put("exito", "Has sido registrado exitosamente.");
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "El nombre de usuario o contraseña son incorrectos.");

        }
        if (logout != null) {
            model.put("logout", "Ha salido correctamente de la plataforma.");

        }
        return "login.html";
    }

    
    @GetMapping("/indexUser")
    public String indexUser(HttpSession session, ModelMap model) {
        return "indexUser.html";
    }
    
    

}
