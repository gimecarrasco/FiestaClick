package com.fiestaClick.demo.services;

import com.fiestaClick.demo.entities.UserEntity;
import com.fiestaClick.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author conta
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public void save(String name, String mail, String password){
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setEmail(mail);
        user.setPassword(password);
         
        userRepository.save(user);
    }
    
    
}
