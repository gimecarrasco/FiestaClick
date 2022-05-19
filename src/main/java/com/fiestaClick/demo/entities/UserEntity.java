
package com.fiestaClick.demo.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String names;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String email;
    private String password;
    private Boolean register;

    public UserEntity() {
    }
    
    public UserEntity(String names, Date dateOfBirth, String email, String password, Boolean register) {
        this.names = names;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.register = register;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return names;
    }

    public void setName(String names) {
        this.names = names;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "names=" + names + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", password=" + password + ", register=" + register + '}';
    }
}
