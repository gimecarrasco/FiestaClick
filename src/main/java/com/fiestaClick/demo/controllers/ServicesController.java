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
import org.springframework.beans.factory.annotation.Autowired;
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
    public String catering(ModelMap modelo) {
        List<CateringEntity> caterings = cateringService.listCatering();
        for (CateringEntity catering : caterings) {
            System.out.println(catering); //probando que funcione la línea 35
        }
        modelo.put("caterings", caterings);
        return "catering.html";
    }

    @GetMapping("/persistCateringAndExtra")
    public String form(ModelMap modelo) {
        return "persistCateringAndExtra.html";
    }

    @PostMapping("/register")
    public String save(ModelMap model, @RequestParam String name, @RequestParam Double price, @RequestParam String description, MultipartFile photo) throws ErrorService {
        try {
            System.out.println("Nombre: " + name);
            System.out.println("Precio: " + price);
            System.out.println("Descripción: " + description);
            System.out.println("Foto: " + photo);
//            cateringService.save(name, price, description, photo);
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
        return "redirect:/servicios/persistCateringAndExtra";
    }

//    @GetMapping("/registerEvent")
//    public String formulary(ModelMap vista) {
//        vista.addAttribute("citys", City.values());
//        return "/servicios/persistCateringAndExtra";
//    }

    @PostMapping("/registerEvent")
    public String saveEvent(ModelMap model, @RequestParam Integer capacity, @RequestParam String adress, @RequestParam String name, @RequestParam String description, @RequestParam String decor, MultipartFile photo, @RequestParam Double price) throws ErrorService {
        try {
            System.out.println("Capacidad: " + capacity);
            System.out.println("Dirección: " + adress);
//            System.out.println("Localidad: " + city);


            System.out.println("Nombre: " + name);
            System.out.println("Descripción: " + description);
            System.out.println("Decoración: " + decor);
            System.out.println("Foto: " + photo);
            System.out.println("Precio: " + price);

            eventRoomService.save(capacity, adress, name, description, decor, photo, price);

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
        return "redirect:/servicios/persistCateringAndExtra";
    }

    @GetMapping("/salones")
    public String salones(ModelMap modelo) {
        List<EventRoomEntity> eventRooms = eventRoomService.listEventRoom();
        modelo.put("eventRooms", eventRooms);
        return "eventRoom.html";
    }

    @GetMapping("/entretenimiento")
    public String entretenimiento(ModelMap modelo) {
        List<ExtraServiceEntity> extras = extraService.listCatering();
        modelo.put("extras", extras);
        return "extra.html";
    }

    @PostMapping("/enable")
    public String enable(ModelMap modelo, @RequestParam String id) {
        try {
            cateringService.enable(id);
            modelo.put("exito", "Se agregó al carrito");
        } catch (ErrorService e) {
            e.printStackTrace();
            modelo.put("error", "No se pudo agregar del carrito");
        }
        return "redirect:/servicios/catering";
    }

    @PostMapping("/enableEventRoom")
    public String enableEventRoom(ModelMap modelo, @RequestParam String id) {
        try {
            eventRoomService.enable(id);
            modelo.put("exito", "Se agregó al carrito");
            return "redirect:/servicios/salones";
        } catch (ErrorService e) {
            e.printStackTrace();
            modelo.put("error", "No se pudo agregar del carrito");
            return "redirect:/servicios/salones";
        }

    }

    @PostMapping("/enableExtra")
    public String enableExtra(ModelMap modelo, @RequestParam String id) {
        try {
            extraService.enable(id);
            modelo.put("exito", "Se agregó al carrito");
            return "redirect:/servicios/entretenimiento";
        } catch (ErrorService e) {
            e.printStackTrace();
            modelo.put("error", "No se pudo agregar del carrito");
            return "redirect:/servicios/entretenimiento";
        }

    }

//    @PostMapping("/addCatering")   //user/addCatering ---> debería ir en userController
//    public String addService(ModelMap model, @RequestParam CateringEntity cateringEntity) {
//        partyEntity.setCateringEntity(cateringEntity); 
//        return "index";
//    }
}
