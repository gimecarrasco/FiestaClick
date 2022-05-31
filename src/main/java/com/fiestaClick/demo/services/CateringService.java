package com.fiestaClick.demo.services;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.CateringRepository;
import java.util.List;
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
        catering.setPhotoEntity(photoEntity);

        return cateringRepository.save(catering);

    }

    public void validate(String name, Integer price, String description) throws Exception {
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
    
    
    

//    @Transactional
//    public List<CateringEntity> listCatering() {
//        return cateringRepository.findAll();
//    }
//
//    @Transactional
//    public CateringEntity findCateringbyName(String catering) {
//        return cateringRepository.findCateringbyname(catering);
//    }
    
    

}
