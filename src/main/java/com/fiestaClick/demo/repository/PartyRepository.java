package com.fiestaClick.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import com.fiestaClick.demo.entities.PartyEntity;

/**
 *
 * @author conta
 */
@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, String>{
 
    //no sé si voy a poner la segunda parte de la query
    @Query("SELECT p FROM Party p WHERE p.user.id = :id AND c.baja IS NULL")
    public List<PartyEntity> findPartyByUser(@Param("id") String id);
    
    
}
