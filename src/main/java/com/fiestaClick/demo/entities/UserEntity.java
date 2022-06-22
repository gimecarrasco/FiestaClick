
package com.fiestaClick.demo.entities;

import com.fiestaClick.demo.enumerations.Role;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String lastName;
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String email;
    private String password;
    private String password2; 
    private Boolean register;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToOne
    private PartyEntity partyEntity;

    public UserEntity() {
    }

    public UserEntity(String id, String name, String lastName, Date dateOfBirth, String email, String password, String password2, Boolean register, Role role, PartyEntity partyEntity) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.password2 = password2;
        this.register = register;
        this.role = role;
        this.partyEntity = partyEntity;
    }    

    public PartyEntity getPartyEntity() {
        return partyEntity;
    }

    public void setPartyEntity(PartyEntity partyEntity) {
        this.partyEntity = partyEntity;
    }
    
    public UserEntity(String email, String password, List<GrantedAuthority> permisos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }        

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "id=" + id + ", name=" + name + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", password=" + password + ", password2=" + password2 + ", register=" + register + ", role=" + role + ", partyEntity=" + partyEntity + '}';
    }
    
    
    
}
