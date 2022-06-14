package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.CateringEntity;
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
@RequestMapping("/shop")
public class BasketController {

    @Autowired
    private CateringService cateringService;
    @Autowired
    private ExtraService extraService;
    @Autowired
    private EventRoomService eventRoomService;

    @GetMapping("/basket")
    public String basket(ModelMap modelo) {
        List<CateringEntity> caterings = cateringService.listCatering();
        modelo.put("caterings", caterings);
        return "basket.html";
    }

    @GetMapping("/buy")
    public String buyController() {
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
        
        return"redirect:/basket/shop";
    }
}
