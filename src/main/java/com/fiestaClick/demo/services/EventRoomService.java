package com.fiestaClick.demo.services;

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

@Service
public class EventRoomService {

    @Autowired
    private EventRoomRepository eventRoomRepository;

    
    @Transactional
    public EventRoomEntity save(Integer capacity, String adress, City city, String name, Boolean register, String description, String decor, List<PhotoEntity> photoEntity, Double price) throws ErrorService {
        EventRoomEntity eventRoom = new EventRoomEntity();

        validate(capacity, adress, city, name, description, decor, price);

        eventRoom.setCapacity(capacity);
        eventRoom.setAdress(adress);
        eventRoom.setCity(city);
        eventRoom.setName(name);
        eventRoom.setRegister(register);
        eventRoom.setDescription(description);
        eventRoom.setDecor(decor);
        eventRoom.setDate(new Date());
        eventRoom.setPhotoEntity(photoEntity);
        eventRoom.setPrice(price);

        return eventRoomRepository.save(eventRoom);
    }

    @Transactional
    public EventRoomEntity modify(String id, Integer capacity, String adress, City city, String name, Boolean register, String description, String decor, List<PhotoEntity> photoEntity, Double price) throws ErrorService {
        validate(capacity, adress, city, name, description, decor, price);
        
        Optional<EventRoomEntity> answer = eventRoomRepository.findById(id);
        if(answer.isPresent()){
            EventRoomEntity eventRoom = answer.get();
            eventRoom.setCapacity(capacity);
            eventRoom.setAdress(adress);
            eventRoom.setCity(city);
            eventRoom.setName(name);
            eventRoom.setRegister(register);
            eventRoom.setDescription(description);
            eventRoom.setDecor(decor);
            //revisar
            eventRoom.setDate(new Date());
            eventRoom.setPhotoEntity(photoEntity);
            eventRoom.setPrice(price);
            
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
    private void validate(Integer capacity, String adress, City city, String name, String description, String decor, Double price) throws ErrorService {
        if (capacity == null || capacity == 0) {
            throw new ErrorService("La capacidad no puede ser nula");
        }
        if (adress == null || adress.isEmpty()) {
            throw new ErrorService("La dirección no puede ser nula");
        }
        if (city == null) {
            throw new ErrorService("El departamento no puede ser nulo");
        }
        if (name == null || name.isEmpty()) {
            throw new ErrorService("El nombre no puede ser nulo");
        }
        if (description == null || description.isEmpty()) {
            throw new ErrorService("La descripción no puede ser nula");
        }
        if (decor == null || decor.isEmpty()) {
            throw new ErrorService("La decoración no puede ser nula");
        }
        if (price == null || price == 0) {
            throw new ErrorService("El precio no puede ser nulo");
        }
    }
    

    @Transactional
    public List<EventRoomEntity> listEventRoom(){
        return eventRoomRepository.findAll();
    }
    
    @Transactional
    public EventRoomEntity findEventRoomByName(String eventRoomName){
        return eventRoomRepository.findByName(eventRoomName);
    }

    @Transactional
    public void deleteById(String id){
        eventRoomRepository.deleteById(id);
    }

    

}