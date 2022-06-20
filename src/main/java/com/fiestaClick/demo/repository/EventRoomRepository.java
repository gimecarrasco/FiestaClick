package com.fiestaClick.demo.repository;

import com.fiestaClick.demo.entities.EventRoomEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 54261
 */

@Repository
public interface EventRoomRepository extends JpaRepository<EventRoomEntity, String> {

    @Query("SELECT e FROM EventRoomEntity e WHERE e.name = :name")
    public EventRoomEntity findByName(@Param("name") String name);
    
     @Query("SELECT e FROM EventRoomEntity e WHERE e.bought = true")
    public EventRoomEntity eventRoomBought();
    
//    @Query("SELECT e FROM EvenRoomEntity e WHERE c.city = :city")
//    public List<EventRoomEntity> findByCity(@Param("city") String city);
//    
//    @Query("SELECT e FROM EvenRoomEntity e WHERE c.adress = :adress")
//    public EventRoomEntity findByAdress(@Param("adress") String adress);
}

