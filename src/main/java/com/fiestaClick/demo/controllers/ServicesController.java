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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/servicios")
public class ServicesController {

    @Autowired
    private CateringService cateringService;

    @Autowired
    private EventRoomService eventRoomService;

    @Autowired
    private ExtraService extraService;

    @Autowired
    private PartyService partyService;

    @GetMapping("/catering")
    public String catering(ModelMap modelo) {
        List<CateringEntity> caterings = cateringService.listCatering();
        modelo.put("caterings", caterings);
        return "catering.html";
    }
    
    @GetMapping("/extra")
    public String extra(ModelMap modelo) {
        List<ExtraServiceEntity> extras = extraService.listExtraService();
        for (ExtraServiceEntity extra : extras) {
            System.out.println(extra); //probando que funcione la línea 35
        }
        modelo.put("extras", extras);
        return "extra.html";
    }
    
    @GetMapping("/eventRoom")
    public String eventRoom(ModelMap modelo) {
        List<EventRoomEntity> eventRooms = eventRoomService.listEventRoom();
        for (EventRoomEntity eventRoom : eventRooms) {
            System.out.println(eventRoom); //probando que funcione la línea 35
        }
        modelo.put("eventRooms", eventRooms);
        return "eventRoom.html";
    }
    
    

    @GetMapping("/ticket/catering/{id}")
    public String ticket(ModelMap modelo, @PathVariable String id, HttpSession session) throws Exception {
        System.out.println("cualquier cosa");
        CateringEntity cateringEntity = cateringService.findById(id);
        partyService.save(session.getId(), cateringEntity, null, null);

        return "redirect:/servicios/entretenimiento";
    }

    @GetMapping("/ticket/eventRoom/{id}")
    public String ticketEventRoom(ModelMap modelo, @PathVariable String id, HttpSession session, @RequestParam String partyEntityId) throws Exception {
        System.out.println("cualquier cosa");

        EventRoomEntity eventRoom = eventRoomService.findById(id);
        partyService.modify(id, id, cateringEntity, extraServiceEntity, eventRoom);

        return "redirect:/servicios/entretenimiento";
    }

    @GetMapping("/persistCateringAndExtra")
    public String persistCateringAndExtra(ModelMap modelo) {
        return "persistCateringAndExtra.html";
    }
    
    @GetMapping("/persistEventRoom")
    public String persistEventRoom(ModelMap modelo) {
        return "persistEventRoom.html";
    }
    
     @GetMapping("/persistExtra")
    public String persistExtra(ModelMap modelo) {
        return "persistExtra.html";
    }

    @PostMapping("/registerCatering")
    public String saveCatering(ModelMap model, @RequestParam String name, @RequestParam Double price, @RequestParam String description, MultipartFile photo) throws ErrorService {
        try {
            System.out.println("Nombre: " + name);
            System.out.println("Precio: " + price);
            System.out.println("Descripción: " + description);
            System.out.println("Foto: " + photo);
//            cateringService.save(name, price, description, photo);
            cateringService.save(name, price, description, photo);
            model.put("exito", "Ha sido cargado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", "Error al cargarse su servicio");
            model.put("name", name);
            model.put("price", price);
            model.put("description", description);
            model.put("photo", photo);
            return "redirect:/servicios/persistCateringAndExtra";
        }
        return "redirect:/servicios/catering";
    }
    
    @PostMapping("/registerExtra")
    public String saveExtra(ModelMap model, @RequestParam String name, @RequestParam Double price, @RequestParam String description, MultipartFile photo) throws ErrorService {
        try {
            System.out.println("Nombre: " + name);
            System.out.println("Precio: " + price);
            System.out.println("Descripción: " + description);
            System.out.println("Foto: " + photo);
            extraService.save(name, price, description, photo);
            model.put("exito", "Ha sido cargado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", "Error al cargarse su servicio");
            model.put("name", name);
            model.put("price", price);
            model.put("description", description);
            model.put("photo", photo);
            return "redirect:/servicios/persistCateringAndExtra";
        }
        return "redirect:/servicios/extra";
    }

    @PostMapping("/registerEventRoom")
    public String saveEventRoom(ModelMap model, @RequestParam String name,  @RequestParam String description, MultipartFile photo, @RequestParam Double price) throws ErrorService {
        try {
            System.out.println("Nombre: " + name);
            System.out.println("Precio: " + price);
            System.out.println("Descripción: " + description);
            System.out.println("Foto: " + photo);
//            cateringService.save(name, price, description, photo);
            eventRoomService.save(name, description, photo, price);
            model.put("exito", "Ha sido cargado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", "Error al cargarse su servicio");
            model.put("name", name);
            model.put("price", price);
            model.put("description", description);
            model.put("photo", photo);
            return "redirect:/servicios/persistCateringAndExtra";
        }
        return "redirect:/servicios/eventRoom";
    }

    @GetMapping("/boughtCatering")
    public String boughtCatering(ModelMap modelo, @RequestParam String id) {
        try {
            cateringService.bought(id);
            modelo.put("exito", "Se agregó al carrito");
        } catch (ErrorService e) {
            e.printStackTrace();
            modelo.put("error", "No se pudo agregar del carrito");
        }
        return "redirect:/servicios/catering";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/notBoughtCatering")
    public String notBoughtCatering(ModelMap model, String id) {
        try {
            cateringService.notBought(id);
        } catch (ErrorService ex) {
            Logger.getLogger(BasketController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "redirect:/shop/basket";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/boughtEventRoom")
    public String boughtEventRoom(ModelMap modelo, @RequestParam String id) {
        try {
            eventRoomService.bought(id);
            modelo.put("exito", "Se agregó al carrito");
        } catch (ErrorService e) {
            e.printStackTrace();
            modelo.put("error", "No se pudo agregar del carrito");
        }
        return "redirect:/servicios/eventRoom";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/notBoughtEventRoom")
    public String notBoughtEventRoom(ModelMap model, String id) {
        try {
            eventRoomService.notBought(id);
        } catch (ErrorService ex) {
            Logger.getLogger(BasketController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "redirect:/shop/basket";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/boughtExtra")
    public String boughtExtra(ModelMap modelo, @RequestParam String id) {
        try {
            extraService.bought(id);
            modelo.put("exito", "Se agregó al carrito");
        } catch (ErrorService e) {
            e.printStackTrace();
            modelo.put("error", "No se pudo agregar del carrito");
        }
        return "redirect:/servicios/extra";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/notBoughtExtra")
    public String notBoughtExtra(ModelMap model, String id) {
        try {
            extraService.notBought(id);
        } catch (ErrorService ex) {
            Logger.getLogger(BasketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/shop/basket";
    }
}
