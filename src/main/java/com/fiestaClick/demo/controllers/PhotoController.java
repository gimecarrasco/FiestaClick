package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.service.CateringService;
import com.fiestaClick.demo.service.EventRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Photo")
public class PhotoController {

    @Autowired
    private CateringService cateringService;

    @Autowired
    private EventRoomService eventRoomService;

    @GetMapping("/cateringEntity")
    public ResponseEntity<byte[]> cateringPhoto(@RequestParam String id) throws ErrorService {
        try {
            CateringEntity cateringEntity = cateringService.enable(id);           
            if (cateringEntity.getPhotoEntity() == null) {
                throw new ErrorService("El Comic no tiene una foto");
            }
            byte[] photo = cateringEntity.getPhotoEntity().getContent();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(photo, headers, HttpStatus.OK);
        } catch (ErrorService e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

}
