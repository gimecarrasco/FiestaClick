package com.fiestaClick.demo.services;

import com.fiestaClick.demo.entities.UserEntity;
import com.fiestaClick.demo.enumerations.Role;
import com.fiestaClick.demo.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    @Autowired
    private JavaMailSender emailSender;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void save(String name, String lastName, Date dateOfBirth, String email, String password){
        
        UserEntity userEntity = new UserEntity();
        
        userEntity.setName(name);
        userEntity.setLastName(lastName);
        userEntity.setDateOfBirth(dateOfBirth);
        userEntity.setEmail(email);
        
        userEntity.setPassword(password);
        userEntity.setRegister(Boolean.TRUE);
        userEntity.setRole(Role.USER);
         
        String subject = "Inscripcion a FiestaClick";

        String content = "Gracias por registrarse " + userEntity.getName() + "!";        
        sendEmail(email, subject, content);

        
        userRepository.save(userEntity);
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
            session.setAttribute("usuariosession", u); //traerme el usuario

            User user = new User(u.getEmail(), u.getPassword(), permisos);
            return user;

        } else {
            return null;
        }

    }
    
    public void sendEmail(String email, String subject, String content) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setText(content);
        emailSender.send(mail);
    }
    
    
}