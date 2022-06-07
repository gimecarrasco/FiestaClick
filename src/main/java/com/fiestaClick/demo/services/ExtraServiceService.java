
package com.fiestaClick.demo.services;

import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.ExtraServiceRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExtraServiceService {
    
    @Autowired
    private ExtraServiceRepository extraServiceRepository;
    
    
    
     public void validate(String name, Double price, String description) throws Exception{
        if (name == null || name.trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");  
        }
         if (price == null || price == 0) {
            throw new ErrorService("No puede ser nulo este valor");  
        }
       if (description == null || description.trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");  
        }
        
    }
    
     
     @Transactional
    public ExtraServiceEntity save(String name, Double price, String description,PhotoEntity photoEntity) throws Exception{
        
        validate(name,price,description);
        
        ExtraServiceEntity extraService= new ExtraServiceEntity();
        
        extraService.setName(name);
        extraService.setPrice(price);
        extraService.setDescription(description);
        extraService.setPhotoEntity((List<PhotoEntity>) photoEntity);
        
        return extraServiceRepository.save(extraService);
        
    }
    
     @Transactional
    public ExtraServiceEntity enable(String id) throws ErrorService { //enel ejemplo de perro este metodo devuelve un perro
        Optional<ExtraServiceEntity> answer = extraServiceRepository.findById(id); //valor que puede ser nulo
        if (answer.isPresent()) {
            ExtraServiceEntity extraService = answer.get();
            extraService.setRegister(Boolean.TRUE);
            return extraServiceRepository.save(extraService);
        } else {
            throw new ErrorService("No se encontro un servicio extra con este id");
        }
    }
    
    
    @Transactional
    public ExtraServiceEntity disable(String id) throws ErrorService { 
        Optional<ExtraServiceEntity> answer = extraServiceRepository.findById(id);
        if (answer.isPresent()) {
            ExtraServiceEntity extraService = answer.get();
            extraService.setRegister(Boolean.FALSE);
            return extraServiceRepository.save(extraService);
        } else {
            throw new ErrorService("No se encontro un servicio extra con este id");
        }
    }
    
     @Transactional
    public ExtraServiceEntity modify(String id, String name, Double price, String description, PhotoEntity photoEntity) throws ErrorService, Exception{
         Optional<ExtraServiceEntity> answer = extraServiceRepository.findById(id);
         
          validate(name, price, description);
          
          if (answer.isPresent()) {
            ExtraServiceEntity extraService = answer.get();
            
           extraService.setName(name);
           extraService.setDescription(description);
           extraService.setPrice(price);
           extraService.setPhotoEntity((List<PhotoEntity>) photoEntity);
            
            return extraServiceRepository.save(extraService);

        } else {
            throw new ErrorService("No se encontro el servicio extra que se desea modificar");
        }
         
    }
    
    @Transactional
    public List<ExtraServiceEntity> listCatering() {
        return extraServiceRepository.findAll();
    }
    
    
   
    @Transactional
    public ExtraServiceEntity findExtraServiceByName(String extraService) {
        return extraServiceRepository.findExtraServiceByName(extraService);
    }
    
    @Transactional
    public void deleteById(String id) {
        extraServiceRepository.deleteById(id);
    }

}
