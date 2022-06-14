
package com.fiestaClick.demo.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender jms;
    
     @Async
    public void mailSender(String email, String text, String des) throws Exception{
        SimpleMailMessage mess = new SimpleMailMessage();
        
        mess.setTo(email);
        mess.setSubject(text);
        mess.setText(des);
        mess.setSentDate(new Date());
        
        jms.send(mess);
    }
    
}
