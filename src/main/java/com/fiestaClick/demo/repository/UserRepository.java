
package com.fiestaClick.demo.repository;

import com.fiestaClick.demo.entities.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    public UserEntity findByEmail(@Param("email") String email);
    
    @Query("SELECT u FROM UserEntity u WHERE u.name = :name")
    public List<UserEntity> findByName(@Param("name") String name);
    

//     @Query("SELECT u from UserEntity u WHERE u.register = true ")
//    public List<UserEntity> listUser();

//     @Query("SELECT u from UserEntity u WHERE u.register = true ")
//    public List<UserEntity> listCatering();

    
    
}
