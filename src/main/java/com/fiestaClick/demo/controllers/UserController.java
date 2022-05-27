package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.repository.UserRepository;
import com.fiestaClick.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author conta
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    

}
