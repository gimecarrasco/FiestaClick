package com.fiestaClick.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fiestaClick.demo.entities.PhotoEntity;

/**
 *
 * @author conta
 */
@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, String>{
    
}
