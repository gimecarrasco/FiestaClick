package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.enumerations.City;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.service.CateringService;
import com.fiestaClick.demo.service.EventRoomService;
import com.fiestaClick.demo.service.ExtraService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/catering")
    public String catering(ModelMap model) {
        List<CateringEntity> caterings = cateringService.listCatering();
        model.put("caterings", caterings);
        model.put("exito", "AÑADIDO AL CARRITO");
        return "catering.html";
    }

    @GetMapping("/extra")
    public String extra(ModelMap model) {
        List<ExtraServiceEntity> extras = extraService.listExtraService();
        model.put("exito", "AÑADIDO AL CARRITO");
        model.put("extras", extras);
        return "extra.html";
    }

    @GetMapping("/eventRoom")
    public String eventRoom(ModelMap model) {
        List<EventRoomEntity> eventRooms = eventRoomService.listEventRoom();
        model.put("exito", "AÑADIDO AL CARRITO");
        model.put("eventRooms", eventRooms);
        return "eventRoom.html";
    }

    @GetMapping("/persistCatering")
    public String persistCateringAndExtra(ModelMap modelo) {
        return "persistCatering.html";
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
            return "redirect:/servicios/persistExtra";
        }
        return "redirect:/servicios/extra";
    }

    @PostMapping("/registerEventRoom")
    public String saveEventRoom(ModelMap model, @RequestParam String name, @RequestParam String description, MultipartFile photo, @RequestParam Double price) throws ErrorService {
        try {
            System.out.println("Nombre: " + name);
            System.out.println("Descripción: " + description);
            System.out.println("Foto: " + photo);
            System.out.println("Precio: " + price);

            eventRoomService.save(name, description, photo, price);
            model.put("exito", "Ha sido cargado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", "Error al cargarse su servicio");
            model.put("name", name);
            model.put("description", description);
            model.put("photo", photo);
            model.put("price", price);
            return "redirect:/servicios/persistEventRoom";
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
