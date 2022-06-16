package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.service.CateringService;
import com.fiestaClick.demo.service.EventRoomService;
import com.fiestaClick.demo.service.ExtraService;
import com.fiestaClick.demo.service.PartyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shop")
public class BasketController {

    @Autowired
    private CateringService cateringService;
    @Autowired
    private ExtraService extraService;
    @Autowired
    private EventRoomService eventRoomService;
    @Autowired
    private PartyService partyService;

    @GetMapping("/basket")
    public String basket(ModelMap modelo) {

//        List<PartyEntity> partys = partyService.listParty();
        List<CateringEntity> caterings = cateringService.listCatering();
        List<EventRoomEntity> eventRooms = eventRoomService.listEventRoom();
        List<ExtraServiceEntity> extras = extraService.listCatering();
        modelo.put("caterings", caterings);
        modelo.put("eventRooms", eventRooms);
        modelo.put("extras", extras);

//        modelo.put("partys", partys);
        return "basket.html";
    }

    @GetMapping("/buy")
    public String buy() {
        return "buy.html";
    }

    @PostMapping("/disable")
    public String disable(ModelMap modelo, @RequestParam String id) {
        try {
            cateringService.disable(id);
            modelo.put("exito", "Se eliminó del carrito");
        } catch (ErrorService e) {
            e.printStackTrace();
            modelo.put("error", "No se pudo eliminar del carrito");
        }
        return "redirect:/shop/basket";
    }

    @PostMapping("/disableEvent")
    public String disableEvent(ModelMap modelo, @RequestParam String id) {
        try {
            eventRoomService.disable(id);
            modelo.put("exito", "Se eliminó del carrito");
        } catch (ErrorService e) {
            e.printStackTrace();
            modelo.put("error", "No se pudo eliminar del carrito");
        }
        return "redirect:/shop/basket";
    }

    @PostMapping("/disableExtra")
    public String disableExtra(ModelMap modelo, @RequestParam String id) {
        try {
            extraService.disable(id);
            modelo.put("exito", "Se eliminó del carrito");
        } catch (ErrorService e) {
            e.printStackTrace();
            modelo.put("error", "No se pudo eliminar del carrito");
        }
        return "redirect:/shop/basket";
    }

}
