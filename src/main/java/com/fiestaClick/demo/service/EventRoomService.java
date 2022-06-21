package com.fiestaClick.demo.service;

import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.enumerations.City;
import com.fiestaClick.demo.repository.EventRoomRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fiestaClick.demo.errors.ErrorService;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EventRoomService {

    @Autowired
    private EventRoomRepository eventRoomRepository;
    @Autowired
    private PhotoService photoService;

    @Transactional
    public EventRoomEntity save(String name, String description, MultipartFile photoEntity, Double price) throws ErrorService {
        EventRoomEntity eventRoom = new EventRoomEntity();

        validate(name, description, price);

//        City city
//        eventRoom.setCity(city);
        eventRoom.setName(name);
        eventRoom.setRegister(Boolean.TRUE);
        eventRoom.setBought(Boolean.FALSE);
        eventRoom.setDescription(description);
//        eventRoom.setDate(new Date());
        PhotoEntity photo = photoService.save((MultipartFile) photoEntity);
        eventRoom.setPhotoEntity(photo);
        eventRoom.setPrice(price);

        return eventRoomRepository.save(eventRoom);
    }

    @Transactional
    public EventRoomEntity update(String id, String name, Boolean register, Boolean bought, String description, PhotoEntity photoEntity, Double price) throws ErrorService {
        validate(name, description, price);

        Optional<EventRoomEntity> answer = eventRoomRepository.findById(id);
        if (answer.isPresent()) {
            EventRoomEntity eventRoom = answer.get();
            eventRoom.setName(name);
            eventRoom.setRegister(register);
            eventRoom.setBought(bought);
            eventRoom.setDescription(description);
            eventRoom.setPhotoEntity(photoEntity);
            eventRoom.setPrice(price);

            return eventRoomRepository.save(eventRoom);
        } else {
            throw new ErrorService("No existe el salón solicitado");
        }

    }

    @Transactional
    public EventRoomEntity bought(String id) throws ErrorService {
        Optional<EventRoomEntity> answer = eventRoomRepository.findById(id);
        if (answer.isPresent()) {
            EventRoomEntity eventRoom = answer.get();
            eventRoom.setBought(Boolean.TRUE);
            return eventRoomRepository.save(eventRoom);
        } else {
            throw new ErrorService("No existe el salón solicitado");
        }
    }

    @Transactional
    public EventRoomEntity notBought(String id) throws ErrorService {
        Optional<EventRoomEntity> answer = eventRoomRepository.findById(id);
        if (answer.isPresent()) {
            EventRoomEntity eventRoom = answer.get();
            eventRoom.setBought(Boolean.FALSE);
            return eventRoomRepository.save(eventRoom);
        } else {
            throw new ErrorService("No existe el salón solicitado");
        }
    }

    @Transactional
    public void delete(String id) throws ErrorService {
        Optional<EventRoomEntity> answer = eventRoomRepository.findById(id);
        if (answer.isPresent()) {
            EventRoomEntity eventRoom = answer.get();
            eventRoomRepository.delete(eventRoom);
        } else {
            throw new ErrorService("No existe un salón con el id solicitado");
        }
    }

    @Transactional
    private void validate(String name, String description, Double price) throws ErrorService {
        
//        if (city == null) {
//            throw new ErrorService("El departamento no puede ser nulo");
//        }
//City city
        if (name == null || name.isEmpty()) {
            throw new ErrorService("El nombre no puede ser nulo");
        }
        if (description == null || description.isEmpty()) {
            throw new ErrorService("La descripción no puede ser nula");
        }
 
        if (price == null || price == 0) {
            throw new ErrorService("El precio no puede ser nulo");
        }
    }

    @Transactional
    public List<EventRoomEntity> listEventRoom() {
        return eventRoomRepository.findAll();
    }

    @Transactional
   public EventRoomEntity findById(String id) {
        return eventRoomRepository.findById(id).get();
    }
   
    @Transactional
    public void deleteById(String id) {
        eventRoomRepository.deleteById(id);
    }

}
