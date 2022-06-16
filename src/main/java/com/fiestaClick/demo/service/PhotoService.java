package com.fiestaClick.demo.service;

import com.fiestaClick.demo.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.errors.ErrorService;
import java.util.Optional;

/**
 *
 * @author conta
 */
@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Transactional
    public PhotoEntity save(MultipartFile archive) throws ErrorService {
        if (archive != null && !archive.isEmpty()) {
            try {
                PhotoEntity photo = new PhotoEntity();
                photo.setMime(archive.getContentType());
                photo.setName(archive.getName());
                photo.setContent(archive.getBytes());
                return photoRepository.save(photo);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public PhotoEntity toUpdate(String idPhoto, MultipartFile archive) throws ErrorService {
        if (archive == null) {
            try {
                PhotoEntity photo = new PhotoEntity();
                if (idPhoto != null) {
                    Optional<PhotoEntity> response = photoRepository.findById(idPhoto);
                    if (response.isPresent()) {
                        photo = response.get();
                    }
                }
                photo.setName(archive.getName());
                photo.setMime(archive.getContentType());
                photo.setContent(archive.getBytes());

                return photoRepository.save(photo);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

}
