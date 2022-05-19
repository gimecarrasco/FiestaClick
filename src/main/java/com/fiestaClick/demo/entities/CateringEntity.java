
package com.fiestaClick.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class CateringEntity {
   
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String name;
    private Integer price;
    private String description;
    private String photo;
    private Boolean register;

    public CateringEntity() {
    }

    public CateringEntity(String name, Integer price, String description, String photo, Boolean register) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.photo = photo;
        this.register = register;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    @Override
    public String toString() {
        return "Catering{" + "id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", photo=" + photo + ", register=" + register + '}';
    }
}
