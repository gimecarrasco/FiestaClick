package com.fiestaClick.demo.repository;

import com.fiestaClick.demo.entities.CateringEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CateringRepository extends JpaRepository<CateringEntity, String> {

//    @Query("SELECT c from CateringEntity c WHERE c.register = true ")
//    public List<CateringEntity> listCatering();
    @Query("SELECT c FROM CateringEntity c WHERE c.name = :name")
    public CateringEntity findCateringByName(@Param("name") String name);
    
    @Query("SELECT c FROM CateringEntity c WHERE c.bought = true")
    public CateringEntity cateringBought();
    
    



//     @Query("SELECT c FROM CateringEntity c WHERE c.price = :price")
//    public CateringEntity findCateringbyprice(@Param("price") Integer price);
//    @Query("select f from PartyEntity f join CateringEntity c where c.name like %?1%")
//    List<CateringEntity> findByCatering(String x);
}
