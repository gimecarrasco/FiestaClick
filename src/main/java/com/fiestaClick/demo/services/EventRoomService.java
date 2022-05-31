package com.fiestaClick.demo.services;


import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.enumerations.City;
import com.fiestaClick.demo.repository.EventRoomRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fiestaClick.demo.errors.ErrorService;

@Service
public class EventRoomService {
    @Autowired
    private EventRoomRepository eventRoomRepository;
    
    public void save(Integer capacity, String adress, City city, String name, Boolean register, String description, String decor, String picture, Double price) throws ErrorService{
        EventRoomEntity eventRoom = new EventRoomEntity();
        
        validate(capacity, adress, city, name, register, description, decor, picture, price);
        
        eventRoom.setCapacity(capacity);
        eventRoom.setAdress(adress);
        eventRoom.setCity(city);
        eventRoom.setName(name);
        eventRoom.setRegister(register);
        eventRoom.setDescription(description);
        eventRoom.setDecor(decor);
        eventRoom.setDate(new Date());
        eventRoom.setPicture(picture);
        eventRoom.setPrice(price);
        
        
        eventRoomRepository.save(eventRoom);
    }
    
    
    public void validate(Integer capacity, String adress, City city, String name, Boolean register, String description, String decor, String picture, Double price) throws ErrorService{
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
        
        //falta validar el resto de los atributos
      
    }
    
    
    
    
    
    
}
