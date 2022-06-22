package com.fiestaClick.demo.service;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.CateringRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CateringService {

    @Autowired
    private CateringRepository cateringRepository;
    @Autowired
    private PhotoService photoService;

    @Transactional
    public CateringEntity save(String name, Double price, String description, MultipartFile photoEntity) throws Exception {

        validate(name, price, description);

        CateringEntity catering = new CateringEntity();

        catering.setName(name);
        catering.setPrice(price);
        catering.setDescription(description);
        PhotoEntity photo = photoService.save((MultipartFile) photoEntity);
        catering.setPhotoEntity(photo);
        catering.setRegister(Boolean.TRUE);
        catering.setBought(Boolean.FALSE);

        return cateringRepository.save(catering);

    }

    public void validate(String name, Double price, String description) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new ErrorService("No puede ser nulo el nombre ");
        }
        if (price == null || price == 0) {
            throw new ErrorService("No puede ser nulo el precio");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new ErrorService("No puede ser nula la descripción");
        }
    }

     @Transactional
    public CateringEntity bought(String id) throws ErrorService {
        Optional<CateringEntity> answer = cateringRepository.findById(id);
        if (answer.isPresent()) {
            CateringEntity cateringEntity = answer.get();
            cateringEntity.setBought(Boolean.TRUE);
            return cateringRepository.save(cateringEntity);
        } else {
            throw new ErrorService("No existe el salón solicitado");
        }
    }

    @Transactional
    public CateringEntity notBought(String id) throws ErrorService {
        Optional<CateringEntity> answer = cateringRepository.findById(id);
        if (answer.isPresent()) {
            CateringEntity cateringEntity = answer.get();
            cateringEntity.setBought(Boolean.FALSE);
            return cateringRepository.save(cateringEntity);
        } else {
            throw new ErrorService("No existe el salón solicitado");
        }
    }

    @Transactional
    public CateringEntity update(String id, String name, Double price, String description, PhotoEntity photoEntity) throws ErrorService, Exception {
        Optional<CateringEntity> answer = cateringRepository.findById(id);

        validate(name, price, description);

        if (answer.isPresent()) {
            CateringEntity catering = answer.get();

            catering.setName(name);
            catering.setDescription(description);
            catering.setPrice(price);
            catering.setPhotoEntity(photoEntity);

            return cateringRepository.save(catering);

        } else {
            throw new ErrorService("No se encontro el catering que se desea modificar");
        }

    }

    @Transactional
    public void delete(String id) throws ErrorService {
        Optional<CateringEntity> answer = cateringRepository.findById(id);
        if (answer.isPresent()) {
            CateringEntity catering = answer.get();
            cateringRepository.delete(catering);
        } else {
            throw new ErrorService("No existe un catering con el id solicitado");
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
    public CateringEntity findById(String id) {

        return cateringRepository.findById(id).get();

    }

    @Transactional
    public void deleteById(String id) {
        cateringRepository.deleteById(id);
    }

}
