
package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.service.CateringService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/catering")
public class CateringController {
    
    @Autowired
    private CateringService cateringService;
    
    @GetMapping("/saveCateringAndExtra")
    public String form(ModelMap modelo)  {
        return "saveCateringAndExtra.html";
    }
   
    
    @PostMapping("/register")
    public String save(ModelMap model, @RequestParam String name, @RequestParam Double price, @RequestParam String description, MultipartFile photo)  throws ErrorService{
        try {
            System.out.println("Nombre: " + name );
            System.out.println("Precio: " + price );
            System.out.println("Descripción: " + description );
            System.out.println("Foto: " + photo );
             cateringService.save(name, price, description, photo);
            //model.put("exito", "Ha sido cargado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", "Error al cargarse su servicio");
            return "saveCateringAndExtra.html";
        }
        return "saveCateringAndExtra.html";
    }
}
