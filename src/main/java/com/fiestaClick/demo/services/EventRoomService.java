package com.fiestaClick.demo.services;

import com.fiestaClick.demo.Enum.City;
import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.repository.EventRoomRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventRoomService {
    @Autowired
    private EventRoomRepository eventRoomRepository;
    
    public void save(Integer capacity, String adress, City city, String name, Boolean register, String description, String decor, String picture, Double price){
        EventRoomEntity eventRoom = new EventRoomEntity();
        
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
    
    
    
    
}
