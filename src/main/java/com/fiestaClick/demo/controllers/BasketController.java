package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.CateringRepository;
import com.fiestaClick.demo.repository.EventRoomRepository;
import com.fiestaClick.demo.repository.ExtraServiceRepository;
import com.fiestaClick.demo.service.CateringService;
import com.fiestaClick.demo.service.EventRoomService;
import com.fiestaClick.demo.service.ExtraService;
import com.fiestaClick.demo.service.PartyService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class BasketController {

    @Autowired
    private CateringRepository cateringRepository;
    @Autowired
    private ExtraServiceRepository extraServiceRepository;
    @Autowired
    private EventRoomRepository eventRoomRepository;
    @Autowired
    private CateringService cateringService;
    @Autowired
    private ExtraService extraService;
    @Autowired
    private EventRoomService  eventRoomService ;
    @Autowired
    private PartyService partyService;

    @GetMapping("/basket")
    public String basket(ModelMap modelo) {

//        List<PartyEntity> partys = partyService.listParty();
        List<CateringEntity> caterings = cateringRepository.listCateringByBought(Boolean.TRUE);
        List<EventRoomEntity> eventRooms = eventRoomRepository.listEventRoomByBought(Boolean.TRUE);
        List<ExtraServiceEntity> extras = extraServiceRepository.listExtraServiceByBought(Boolean.TRUE);
        modelo.put("caterings", caterings);
        modelo.put("eventRooms", eventRooms);
        modelo.put("extras", extras);

//        modelo.put("partys", partys);
        return "basket.html";
    }

    @PostMapping("/notBoughtCatering")
    public String notBoughtCatering(HttpSession session, ModelMap model, String id) {
        try {
            cateringService.notBought(id);
            return "basket.html";
        } catch (ErrorService ex) {
            Logger.getLogger(BasketController.class.getName()).log(Level.SEVERE, null, ex);
            return "basket.html";
        }
    }
//
//    @PostMapping("/disableCatering")
//    public String disableCatering(ModelMap modelo, @RequestParam String id) {
//        try {
//            cateringService.disable(id);
//            modelo.put("exito", "Se eliminó del carrito");
//        } catch (ErrorService e) {
//            e.printStackTrace();
//            modelo.put("error", "No se pudo eliminar del carrito");
//        }
//        return "redirect:/shop/basket";
//    }
//
//    @PostMapping("/disableEvent")
//    public String disableEvent(ModelMap modelo, @RequestParam String id) {
//        try {
//            eventRoomService.disable(id);
//            modelo.put("exito", "Se eliminó del carrito");
//        } catch (ErrorService e) {
//            e.printStackTrace();
//            modelo.put("error", "No se pudo eliminar del carrito");
//        }
//        return "redirect:/shop/basket";
//    }
//
//    @PostMapping("/disableExtra")
//    public String disableExtra(ModelMap modelo, @RequestParam String id) {
//        try {
//            extraService.disable(id);
//            modelo.put("exito", "Se eliminó del carrito");
//        } catch (ErrorService e) {
//            e.printStackTrace();
//            modelo.put("error", "No se pudo eliminar del carrito");
//        }
//        return "redirect:/shop/basket";
//    }

    
    // ESTAS ACCIONES LAS PUEDE HACER UNICAMENTE UN ADMINISTRADOR
//    @PostMapping("/deleteCatering")
//    public String deleteCatering(ModelMap modelo, @RequestParam String id) {
//        try {
//            cateringService.delete(id);
//            modelo.put("exito", "Se eliminó correctamente.");
//
//        } catch (ErrorService e) {
//            e.printStackTrace();
//            modelo.put("error", "No se pudo eliminar ");
//        }
//        return "redirect:/shop/basket";
//    }
//
//    @PostMapping("/deleteEventRoom")
//    public String deleteEventRoom(ModelMap modelo, @RequestParam String id) {
//        try {
//            eventRoomService.delete(id);
//            modelo.put("exito", "Se eliminó correctamente.");
//
//        } catch (ErrorService e) {
//            e.printStackTrace();
//            modelo.put("error", "No se pudo eliminar ");
//        }
//        return "redirect:/shop/basket";
//    }
//
//    @PostMapping("/deleteExtra")
//    public String deleteExtra(ModelMap modelo, @RequestParam String id) {
//        try {
//            extraService.delete(id);
//            modelo.put("exito", "Se eliminó correctamente.");
//
//        } catch (ErrorService e) {
//            e.printStackTrace();
//            modelo.put("error", "No se pudo eliminar ");
//        }
//        return "redirect:/shop/basket";
//    }

}
