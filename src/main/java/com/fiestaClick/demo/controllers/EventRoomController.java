
package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.services.EventRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventRoom")
public class EventRoomController {
     
    @Autowired
    private EventRoomService eventRoomService;

}
