

package com.fiestaClick.demo.repository;

import com.fiestaClick.demo.entities.ExtraServiceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ExtraServiceRepository extends JpaRepository<ExtraServiceEntity, String> {
    
//    @Query("SELECT e from ExtraServiceEntity e WHERE e.register = true ")
//    public List<ExtraServiceEntity> listExtraService();
    
    @Query("SELECT e FROM ExtraServiceEntity e WHERE e.name = :name")
    public ExtraServiceEntity findExtraServiceByName(@Param("name") String name);
    
@Query("SELECT e FROM ExtraServiceEntity e WHERE e.bought = true")
    public ExtraServiceEntity extraServiceBought();
    
//    
//    @Query("SELECT e FROM ExtraServiceEntity e WHERE e.price = :price")
//    public ExtraServiceEntity findExtraServicebyprice(@Param("price") Integer price);
//    
//    @Query("select f from PartyEntity f join ExtraServiceEntity e where e.name like %?1%")
//    List<ExtraServiceEntity> findByExtra(String x);
//    
    
}
