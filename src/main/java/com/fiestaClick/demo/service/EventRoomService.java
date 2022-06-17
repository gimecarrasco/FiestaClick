package com.fiestaClick.demo.service;

import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.repository.EventRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fiestaClick.demo.errors.ErrorService;
import static java.lang.Boolean.TRUE;
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
    public EventRoomEntity save(String name, Double price, String description, MultipartFile photoEntity) throws ErrorService {
        EventRoomEntity eventRoom = new EventRoomEntity();

        validate(name, price, description);

        eventRoom.setName(name);
        eventRoom.setPrice(price);
        eventRoom.setDescription(description);
        PhotoEntity photo = photoService.save((MultipartFile) photoEntity);
        eventRoom.setPhotoEntity(photo);
        eventRoom.setRegister(Boolean.TRUE);

        return eventRoomRepository.save(eventRoom);
    }

    @Transactional
    public EventRoomEntity modify(String id, String name, Double price, String description, Boolean register, PhotoEntity photoEntity) throws ErrorService {
        validate(name, price, description);

        Optional<EventRoomEntity> answer = eventRoomRepository.findById(id);
        if (answer.isPresent()) {
            EventRoomEntity eventRoom = answer.get();

            eventRoom.setName(name);
            eventRoom.setPrice(price);
            eventRoom.setDescription(description);
            eventRoom.setRegister(register);

            eventRoom.setPhotoEntity(photoEntity);

            return eventRoomRepository.save(eventRoom);
        } else {
            throw new ErrorService("No existe el salón solicitado");
        }

    }

    @Transactional
    public EventRoomEntity enable(String id) throws ErrorService {
        Optional<EventRoomEntity> answer = eventRoomRepository.findById(id);
        if (answer.isPresent()) {
            EventRoomEntity eventRoom = answer.get();
            eventRoom.setRegister(Boolean.TRUE);
            return eventRoomRepository.save(eventRoom);
        } else {
            throw new ErrorService("No existe el salón solicitado");
        }
    }

    @Transactional
    public EventRoomEntity disable(String id) throws ErrorService {
        Optional<EventRoomEntity> answer = eventRoomRepository.findById(id);
        if (answer.isPresent()) {
            EventRoomEntity eventRoom = answer.get();
            eventRoom.setRegister(Boolean.FALSE);
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
    private void validate(String name, Double price, String description) throws ErrorService {
//        if (capacity == null || capacity == 0) {
//            throw new ErrorService("La capacidad no puede ser nula");
//        }
//        if (adress == null || adress.isEmpty()) {
//            throw new ErrorService("La dirección no puede ser nula");
//        }
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
//        if (decor == null || decor.isEmpty()) {
//            throw new ErrorService("La decoración no puede ser nula");
//        }
        if (price == null || price == 0) {
            throw new ErrorService("El precio no puede ser nulo");
        }
    }

    @Transactional
    public List<EventRoomEntity> listEventRoom() {
        return eventRoomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public EventRoomEntity findById(String id) {
        return eventRoomRepository.findById(id).get();
    }

    @Transactional
    public EventRoomEntity findEventRoomByName(String eventRoomName) {
        return eventRoomRepository.findByName(eventRoomName);
    }

    @Transactional
    public void deleteById(String id) {
        eventRoomRepository.deleteById(id);
    }

}
