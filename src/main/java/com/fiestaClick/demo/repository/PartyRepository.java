package com.fiestaClick.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fiestaClick.demo.entities.PartyEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author conta
 */
@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, String>{
 
    //Probar con este metodo
//	public Optional<PartyEntity> findPartyByUserId(String id);
	//Y si tira error probrar hacer una query
	
    @Query("SELECT p FROM PartyEntity p JOIN UserEntity u WHERE u.id LIKE :id")
    Optional<PartyEntity> findPartyByUserId(@Param("id") String id);
        
    //no sé si voy a poner la segunda parte de la query
//    @Query("SELECT p FROM PartyEntity p WHERE p.user.id = :id")
//    public Optional<PartyEntity> findPartyByUserId(@Param("id") String id);
//////    
    
}
