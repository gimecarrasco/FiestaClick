package com.fiestaClick.demo.service;

import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.ExtraServiceRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExtraService {

    @Autowired
    private ExtraServiceRepository extraServiceRepository;
    @Autowired
    private PhotoService photoService;

    public void validate(String name, Double price, String description) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new ErrorService("El nombre no puede ser nulo.");
        }
        if (price == null || price == 0) {
            throw new ErrorService("El precio no puede ser nulo.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new ErrorService("La descripción no puede ser nula.");
        }

    }

    @Transactional
    public ExtraServiceEntity save(String name, Double price, String description, MultipartFile photoEntity) throws Exception {

        validate(name, price, description);

        ExtraServiceEntity extraService = new ExtraServiceEntity();

        extraService.setName(name);
        extraService.setPrice(price);
        extraService.setDescription(description);
        extraService.setRegister(Boolean.TRUE);
        extraService.setBought(Boolean.FALSE);
        PhotoEntity photo = photoService.save((MultipartFile) photoEntity);
        extraService.setPhotoEntity(photo);

        return extraServiceRepository.save(extraService);

    }

     @Transactional
    public ExtraServiceEntity bought(String id) throws ErrorService {
        Optional<ExtraServiceEntity> answer = extraServiceRepository.findById(id);
        if (answer.isPresent()) {
            ExtraServiceEntity extraEntity = answer.get();
            extraEntity.setBought(Boolean.TRUE);
            return extraServiceRepository.save(extraEntity);
        } else {
            throw new ErrorService("No existe el salón solicitado");
        }
    }

    @Transactional
    public ExtraServiceEntity notBought(String id) throws ErrorService {
        Optional<ExtraServiceEntity> answer = extraServiceRepository.findById(id);
        if (answer.isPresent()) {
            ExtraServiceEntity extraEntity = answer.get();
            extraEntity.setBought(Boolean.FALSE);
            return extraServiceRepository.save(extraEntity);
        } else {
            throw new ErrorService("No existe el salón solicitado");
        }
    }

    @Transactional
    public ExtraServiceEntity update(String id, String name, Double price, String description, PhotoEntity photoEntity) throws ErrorService, Exception {
        Optional<ExtraServiceEntity> answer = extraServiceRepository.findById(id);

        validate(name, price, description);

        if (answer.isPresent()) {
            ExtraServiceEntity extraService = answer.get();

            extraService.setName(name);
            extraService.setDescription(description);
            extraService.setPrice(price);
            extraService.setPhotoEntity((PhotoEntity) photoEntity);

            return extraServiceRepository.save(extraService);

        } else {
            throw new ErrorService("No se encontro el servicio extra que se desea modificar");
        }

    }

    @Transactional
    public List<ExtraServiceEntity> listExtraService() {
        return extraServiceRepository.findAll();
    }

    @Transactional
    public ExtraServiceEntity findExtraServiceByName(String extraService) {
        return extraServiceRepository.findExtraServiceByName(extraService);
    }
    
    @org.springframework.transaction.annotation.Transactional
   public ExtraServiceEntity findById(String id) {
        return extraServiceRepository.findById(id).get();
    }
   
    @Transactional
    public void deleteById(String id) {
        extraServiceRepository.deleteById(id);
    }

}
