package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.entities.PartyEntity;
import com.fiestaClick.demo.entities.UserEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.CateringRepository;
import com.fiestaClick.demo.service.CateringService;
import com.fiestaClick.demo.service.EventRoomService;
import com.fiestaClick.demo.service.ExtraService;
import com.fiestaClick.demo.service.PartyService;
import com.fiestaClick.demo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @Autowired
    private UserService userService;
    
    @Autowired
    private CateringRepository cR;

    @GetMapping("/basket/{idUser}")
    public String basket(ModelMap model, @RequestParam(value = "idUser") String idUser, @RequestParam(value = "idCatering") String idCatering) {
    	try {
    	PartyEntity party = partyService.findPartyByIdUser(idUser);
        partyService.updateCatering(party.getId(),idCatering);
//        List<CateringEntity> caterings = cateringService.listCatering();
//        List<EventRoomEntity> eventRooms = eventRoomService.listEventRoom();
//        List<ExtraServiceEntity> extras = extraService.listCatering();
//        model.put("caterings", caterings);
//        model.put("eventRooms", eventRooms);
//        model.put("extras", extras);

        model.put("party", party);
        return "basket.html";
    	}catch(Exception e) {
    		model.put("error",e.getMessage());
    		return "basket.html";
    	}
    }
    
    @PostMapping("/basket/{idUser}/{idCatering}")
    public String updateCatering(ModelMap model, @RequestParam(value = "idUser")String idUser, @RequestParam(value = "idCatering")String idCatering ) {
    	   System.out.println("entró al método");
        try {
    		UserEntity user = userService.findById(idUser);
    		//Pasar por el servicio de usuario.
    		String response = partyService.updateCatering(user.getPartyEntity().getId(), idCatering);
    		model.put("MensajeRespuesta", response);
                return "redirect:/servicios/catering";
    	}catch(Exception e) {
    		//Model put Error
    		model.put("Error", e.getMessage());                
    		return "redirect:/servicios/catering";
    	}
    }
    

    @GetMapping("/buy")
    public String buy() {
        return "buy.html";
    }

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
