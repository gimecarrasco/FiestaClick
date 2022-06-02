package com.fiestaClick.demo.services;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.CateringRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CateringService {

    @Autowired
    private CateringRepository cateringRepository;

    @Transactional
    public CateringEntity save(String name, Integer price, String description, PhotoEntity photoEntity) throws Exception {

        validate(name, price, description);

        CateringEntity catering = new CateringEntity();

        catering.setName(name);
        catering.setPrice(price);
        catering.setDescription(description);
        catering.setPhotoEntity((List<PhotoEntity>) photoEntity);

        return cateringRepository.save(catering);

    }

    public void validate(String name, Integer price, String description) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");
        }
        if (price == null || price.toString().trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo este valor");
        }
        if (description == null || description.trim().isEmpty() || description.length() < 200) {
            throw new ErrorService("No puede ser nulo este valor");
        }
    }
    
     @Transactional
    public CateringEntity enable(String id) throws ErrorService { //enel ejemplo de perro este metodo devuelve un perro
        Optional<CateringEntity> answer = cateringRepository.findById(id);
        if (answer.isPresent()) {
            CateringEntity catering = answer.get();
            catering.setRegister(Boolean.TRUE);
            return cateringRepository.save(catering);
        } else {
            throw new ErrorService("No existe un catering con el id solicitado");
        }
    }
    
    
    @Transactional
    public CateringEntity disable(String id) throws ErrorService { 
        Optional<CateringEntity> answer = cateringRepository.findById(id);
        if (answer.isPresent()) {
            CateringEntity catering = answer.get();
            catering.setRegister(Boolean.FALSE);
            return cateringRepository.save(catering);
        } else {
            throw new ErrorService("No existe un catering con el id solicitado");
        }
    }
    
     @Transactional
    public CateringEntity modify(String id, String name, Integer price, String description, PhotoEntity photoEntity) throws ErrorService, Exception{
         Optional<CateringEntity> answer = cateringRepository.findById(id);
         
          validate(name, price, description);
          
          if (answer.isPresent()) {
            CateringEntity catering = answer.get();
            
           catering.setName(name);
           catering.setDescription(description);
           catering.setPrice(price);
           catering.setPhotoEntity((List<PhotoEntity>) photoEntity);
            
            return cateringRepository.save(catering);

        } else {
            throw new ErrorService("No se encontro el catering que se desea modificar");
        }
         
    }
    
    @Transactional
    public List<CateringEntity> listCatering() {
        return cateringRepository.findAll();
    }
    
    
   
    @Transactional
    public CateringEntity findCateringByName(String catering) {
        return cateringRepository.findCateringByName(catering);
    }
    
    @Transactional
    public void deleteById(String id) {
        cateringRepository.deleteById(id);
    }

}
