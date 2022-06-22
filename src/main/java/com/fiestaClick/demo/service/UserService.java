package com.fiestaClick.demo.service;

import com.fiestaClick.demo.entities.PartyEntity;
import com.fiestaClick.demo.entities.UserEntity;
import com.fiestaClick.demo.enumerations.Role;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 *
 * @author conta
 */
@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
//    @Autowired
//    private JavaMailSender emailSender;
    
    @Autowired
    private MailService mailService;
    
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void save(String name, String lastName, Date dateOfBirth, String email, String password, String password2) throws Exception{
        
        validate(name, lastName, dateOfBirth, email, password, password2);
        UserEntity userEntity = new UserEntity();
        
        userEntity.setName(name);
        userEntity.setLastName(lastName);
        userEntity.setDateOfBirth(dateOfBirth);
        userEntity.setEmail(email); 
        
        String encripted = new BCryptPasswordEncoder().encode(password);
        userEntity.setPassword(encripted); 
        
        String encripted2 = new BCryptPasswordEncoder().encode(password2);
        userEntity.setPassword(encripted2); 
                
        userEntity.setRegister(Boolean.TRUE);
        userEntity.setRole(Role.USER);
         
       // String subject = "Inscripcion a FiestaClick";

        //String content = "Gracias por registrarse " + userEntity.getName() + "!";        
        //sendEmail(email, subject, content);

        mailService.mailSender(email, "bienvenido/a"+" "+name, "Se ha registrado con exito a FiestaClick");
        
        userRepository.save(userEntity);
    }
    
    public void validate(String name, String lastName, Date dateOfBirth, String email, String password, String password2) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new ErrorService("El nombre no puede ser nulo el nombre ");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new ErrorService("El apellido no puede ser nulo.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new ErrorService("No puede ser nula la descripción");
        }
        if (password==null || password.trim().isEmpty()) {
            throw new ErrorService("La contraseña no puede ser nula.");
        }
        if (!password.equals(password2)) {
            throw new ErrorService("Las contraseñas deben coincidir.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity u= userRepository.findByEmail(email);
        if (u!= null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + u.getRole());//ROLE_ADMIN O ROLE_USER
            permisos.add(p1);

            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usersession", u); //traerme el usuario

            User user = new User(u.getEmail(), u.getPassword(), permisos);
            return user;

        } else {
            return null;
        }
    }

        @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void update(String id, String name, String lastName, Date dateOfBirth, String email, String password, String password2) throws Exception{
        validate(name, lastName, dateOfBirth, email, password, password2);

        Optional<UserEntity> answer = userRepository.findById(id);
        if (answer.isPresent()) {
            UserEntity user = answer.get();
            user.setName(name);
            user.setLastName(lastName);
            user.setDateOfBirth(dateOfBirth);
            user.setName(email);
            
            String encripted = new BCryptPasswordEncoder().encode(password);
            user.setPassword(encripted); 
            
            String encripted2 = new BCryptPasswordEncoder().encode(password);
            user.setPassword(encripted2); 
            
            userRepository.save(user);
        } else {
            throw new ErrorService("No se encontró un autor con el identificador solicitado.");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void desable(String id) throws ErrorService{
        Optional<UserEntity> answer =  userRepository.findById(id);
        if (answer.isPresent()) {
            UserEntity user = answer.get();
            user.setRegister(Boolean.FALSE);
        } else {
            throw new ErrorService("No se encontró un autor con el identificador solicitado.");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void enable(String id) throws ErrorService{
         Optional<UserEntity> answer =  userRepository.findById(id);
        if (answer.isPresent()) {
            UserEntity autor = answer.get();
            autor.setRegister(Boolean.TRUE);
        } else {
            throw new ErrorService("No se encontró un autor con el identificador solicitado.");
        } 
    }
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId(String id) throws ErrorService{
        Optional <UserEntity> answer = userRepository.findById(id);
        if (answer.isPresent()) {
           userRepository.delete(answer.get());
        } else {
             throw new ErrorService("No se encontró un autor con el identificador solicitado.");
        }
    }
    
      @Transactional(propagation = Propagation.NESTED)
    public UserEntity findById(String id) {
        return userRepository.findById(id).get();
    }
   
    
//    public void sendEmail(String email, String subject, String content) {
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setTo(email);
//        mail.setSubject(subject);
//        mail.setText(content);
//        emailSender.send(mail);
//    }
    
    
}