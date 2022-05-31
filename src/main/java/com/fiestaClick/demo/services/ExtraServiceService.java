
package com.fiestaClick.demo.services;

import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.ExtraServiceRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExtraServiceService {
    
    @Autowired
    private ExtraServiceRepository extraServiceRepository;
    
    
    
     public void validate(String name, Integer price, String description) throws Exception{
        if (name == null || name.trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");  
        }
         if (price == null || price.toString().trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");  
        }
       if (description == null || description.trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");  
        }
        
    }
    
     
     @Transactional
    public ExtraServiceEntity save(String name, Integer price, String description,PhotoEntity photoEntity) throws Exception{
        
        validate(name,price,description);
        
        ExtraServiceEntity extraService= new ExtraServiceEntity();
        
        extraService.setName(name);
        extraService.setPrice(price);
        extraService.setDescription(description);
        extraService.setPhotoEntity(photoEntity);
        
        return extraServiceRepository.save(extraService);
        
    }
    
    
}
