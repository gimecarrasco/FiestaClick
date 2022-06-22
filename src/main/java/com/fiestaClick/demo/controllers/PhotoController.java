package com.fiestaClick.demo.controllers;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.service.CateringService;
import com.fiestaClick.demo.service.EventRoomService;
import com.fiestaClick.demo.service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private CateringService cateringService;

    @Autowired
    private EventRoomService eventRoomService;

    @Autowired
    private ExtraService extraService;

    @GetMapping("/cateringEntity/{id}")
    public ResponseEntity<byte[]> cateringPhoto(@PathVariable String id) throws ErrorService {
        try {
            CateringEntity cateringEntity = cateringService.findById(id);
            if (cateringEntity.getPhotoEntity() == null) {
                throw new ErrorService("El Catering no tiene una foto");
            }
            byte[] photo = cateringEntity.getPhotoEntity().getContent();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(photo, headers, HttpStatus.OK);
        } catch (ErrorService e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/eventRoomEntity/{id}")
    public ResponseEntity<byte[]> eventRoomPhoto(@PathVariable String id) throws ErrorService {
        try {
            EventRoomEntity eventRoomEntity = eventRoomService.findById(id);
            if (eventRoomEntity.getPhotoEntity() == null) {
                throw new ErrorService("El Salón no tiene una foto");
            }
            byte[] photo = eventRoomEntity.getPhotoEntity().getContent();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(photo, headers, HttpStatus.OK);
        } catch (ErrorService e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/extraServiceEntity/{id}")
    public ResponseEntity<byte[]> extraPhoto(@PathVariable String id) throws ErrorService {
        try {
            ExtraServiceEntity extraServiceEntity = extraService.findById(id);
            if (extraServiceEntity.getPhotoEntity() == null) {
                throw new ErrorService("El Servicio no tiene una foto");
            }
            byte[] photo = extraServiceEntity.getPhotoEntity().getContent();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(photo, headers, HttpStatus.OK);
        } catch (ErrorService e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
