package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.UserEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.UserRepository;
import com.fiestaClick.demo.service.UserService;
import com.sun.istack.logging.Logger;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/register")
    public String save(ModelMap model, @RequestParam String name, @RequestParam String lastName, @RequestParam Date dateOfBirth, @RequestParam String email, @RequestParam String password, @RequestParam String password2) throws ErrorService {
        try {
            userService.save(name, lastName, dateOfBirth, email, password, password2);
            model.put("exito", "Felicitaciones!");
        } catch (Exception e) {
            e.printStackTrace();
            //model.put("error", e.getMessage());
            model.put("name", name);
            model.put("lastName", lastName);
            model.put("dateOfBirth", dateOfBirth);
            model.put("email", email);
            model.put("password", password);
            model.put("password", password2);
            //Logger.getLogger(MainController.class.getName().log(Level.SEVERE, null, e));
            return "login.html";
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/editProfile")
    public String editProfile(HttpSession session, @RequestParam String id, ModelMap model) {

        UserEntity login = (UserEntity) session.getAttribute("usersession");
        if (login == null || !login.getId().equals(id)) {
            return "login.html";// NO PUEDO REDIRECCIONAR A OTRO HMTL CUYO GETMAPPING SE ENCUENTRE EN OTRO CONTROLLER
        }

        try {
            UserEntity userEdit = userService.findById(id);
            model.addAttribute("profile", userEdit);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "editProfile";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/updateProfile")
    public String updateProfile(HttpSession session, ModelMap model, @RequestParam String id, @RequestParam String name, @RequestParam String lastName, @RequestParam Date dateOfBirth, @RequestParam String email, @RequestParam String password, @RequestParam String password2) {
        UserEntity user = null;

        try {
            UserEntity login = (UserEntity) session.getAttribute("usersession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/index"; // NO ME FUNCIONA EL REDIRECT
            }

            user = userService.findById(id);
            userService.update(id, name, lastName, dateOfBirth, email, password, password2);
            session.setAttribute("usersession", user);
            return "redirect:/index";
            
        } catch (Exception e) {
            model.put("name", name);
            model.put("lastName", lastName);
            model.put("dateOfBirth", dateOfBirth);
            model.put("email", email);
            model.put("password", password);
            model.put("password", password2);
            return "editProfil.html";
        }

    }

}
