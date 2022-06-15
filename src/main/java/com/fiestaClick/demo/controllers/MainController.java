package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.UserEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.sun.istack.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
         if (error != null) {
             model.put("error", "El nombre de usuario o contraseña son incorrectos.");
             return "login.html";
         }
         if (logout != null) {
             model.put("logout", "Ha salido correctamente de la plataforma.");
             return "login.html";
         }
        return "login.html";
    }
     @PreAuthorize("hasAnyRole('ROLE_USER')") 
        @GetMapping("/indexUser")
  public String indexUser(HttpSession session, ModelMap model) {
//         UserEntity login = (UserEntity) session.getAttribute("usersession");//recupero usuario logueado
//        if(login == null){
//            return "redirect:/login";// si pasa tiempo y no hace nada para vuelva a inicio
//        }
//        try {
//            List<Mascota> listaMascotaPropia = mascotaServicio.buscarPorIdUsuario(login.getId());
//            model.addAttribute("listaMascotasPropia",listaMascotaPropia);
//        } catch (ErrorService e) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
//        }
            
        return "indexUser.html";
    }
  
    @GetMapping("/catering")
    public String catering() {
        return "catering.html";
    }

    @GetMapping("/eventRoom")
    public String eventRoom() {
        return "eventRoom.html";
    }

    @GetMapping("/extra")
    public String extra() {
        return "extra.html";
    }
    
    

    
     
}
