package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
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

@Controller
@RequestMapping("/servicios")
public class ServicesController {

    @Autowired
    private CateringService cateringService;
    @Autowired
    private EventRoomService eventRoomService;
    @Autowired
    private ExtraService extraService;

    @GetMapping("/saveCateringAndExtra")
    public String form(ModelMap modelo) {
        return "saveCateringAndExtra.html";
    }

    @GetMapping("/catering")
    public String catering(ModelMap modelo) {
        List<CateringEntity> caterings = cateringService.listCatering();
        modelo.put("caterings", caterings);
        return "catering.html";
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

    @GetMapping("/edit")
    public String edit(ModelMap modelo, @RequestParam String id) {
        modelo.put("id", id);
        return "prueba.html";
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

    @PostMapping("/modify")
    public String modify(ModelMap modelo, @RequestParam String id, String name, Double price, String description, PhotoEntity photoEntity) {
        try {
            cateringService.modify(id, name, price, description, photoEntity);
            modelo.put("exito", "Modificó el catering");
        } catch (Exception e) {
            e.printStackTrace();
            modelo.put("error", "No se pudo modificar el catering");
        }
        return "redirect:/servicios/catering";
    }

//    @PostMapping("/register")
//    public String save(ModelMap model, @RequestParam String name, @RequestParam Double price, @RequestParam String description, MultipartFile photo) throws ErrorService {
//        try {
////            System.out.println("Nombre: " + name );
////            System.out.println("Precio: " + price );
////            System.out.println("Descripción: " + description );
////            System.out.println("Foto: " + photo );
//            cateringService.save(name, price, description, photo);
//            //model.put("exito", "Ha sido cargado exitosamente.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.put("error", "Error al cargarse su servicio");
//            return "saveCateringAndExtra.html";
//        }
//        return "saveCateringAndExtra.html";
//    }
//    @PostMapping("/addCatering")   //user/addCatering ---> debería ir en userController
//    public String addService(ModelMap model, @RequestParam CateringEntity cateringEntity) {
//        partyEntity.setCateringEntity(cateringEntity); 
//        return "index";
//    }
}
