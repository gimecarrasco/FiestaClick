
package com.fiestaClick.demo.services;

import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.ExtraServiceRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExtraServiceService {
    
    @Autowired
    private ExtraServiceRepository extraServiceRepository;
    
    
    
     public void validate(String name, Integer price, String description,String photo) throws Exception{
        if (name == null || name.trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");  
        }
         if (price == null || price.toString().trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");  
        }
       if (description == null || description.trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");  
        }
        if (photo == null || photo.trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");  
        }
        
    }
    
     
     @Transactional
    public ExtraServiceEntity save(String name, Integer price, String description,String photo) throws Exception{
        
        validate(name,price,description,photo);
        
        ExtraServiceEntity extraService= new ExtraServiceEntity();
        
        extraService.setName(name);
        extraService.setPrice(price);
        extraService.setDescription(description);
        extraService.setPhoto(photo);
        
        return extraServiceRepository.save(extraService);
        
    }
    
    
}
