package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.entities.PartyEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.CateringRepository;
import com.fiestaClick.demo.repository.EventRoomRepository;
import com.fiestaClick.demo.repository.ExtraServiceRepository;
import com.fiestaClick.demo.service.CateringService;
import com.fiestaClick.demo.service.EventRoomService;
import com.fiestaClick.demo.service.ExtraService;
import com.fiestaClick.demo.service.PartyService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private EventRoomService eventRoomService;
    @Autowired
    private PartyService partyService;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/basket")
    public String basket(ModelMap model) {

//        List<PartyEntity> partys = partyService.listParty();
        CateringEntity catering = cateringRepository.cateringBought();
        EventRoomEntity eventRoom = eventRoomRepository.eventRoomBought();
        ExtraServiceEntity extra = extraServiceRepository.extraServiceBought();

        if (catering == null && eventRoom == null && extra == null) {
            model.put("message", "El carrito se encuentra vacío.");
        } else {
            model.put("catering", catering);
            model.put("eventRoom", eventRoom);
            model.put("extra", extra);
        }

//        partyService.save(caterings, extras, eventRoomEntity, partyDate);
//        modelo.put("partys", partys);
        return "basket.html";
    }

    @PostMapping("/registerParty")
    public String registerParty(ModelMap model, String idCatering, String idEventRoom, String idExtra) {
        try {
            CateringEntity catering = cateringService.findById(idCatering);
            ExtraServiceEntity extra = extraService.findById(idExtra);
            EventRoomEntity eventRoom = eventRoomService.findById(idEventRoom);

            PartyEntity party = partyService.save(catering, extra, eventRoom);
            System.out.println("El precio de la fiesta es: " + party.getTotalPrice());
            model.addAttribute("priceParty", party.getTotalPrice().toString());

            return "pay.html";
        } catch (Exception ex) {
            Logger.getLogger(BasketController.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/shop/pay";
        }
    }

    @GetMapping("/pay")
    public String pay() {
//          model.put ("priceParty", partyService.totalPartyPrice(idEventRoom, idCatering, idExtra).toString());
        return "pay.html";
    }

    @GetMapping("/confirmationOfPayment")
    public String confirmationOfPayment(ModelMap model) throws ErrorService {
//          model.put ("priceParty", partyService.totalPartyPrice(idEventRoom, idCatering, idExtra).toString());
        CateringEntity catering = cateringRepository.cateringBought();
        EventRoomEntity eventRoom = eventRoomRepository.eventRoomBought();
        ExtraServiceEntity extra = extraServiceRepository.extraServiceBought();
        cateringService.notBought(catering.getId());
        eventRoomService.notBought(eventRoom.getId());
        extraService.notBought(extra.getId());

        return "confirmationOfPayment.html";
    }

}
