package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/extra")
public class ExtraController {
    
    @Autowired
    private ExtraService extraService;

}
