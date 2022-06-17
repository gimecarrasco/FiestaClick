
package com.fiestaClick.demo.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ExtraServiceEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String name;
    private Double price;
    private String description;    
    private Boolean register;
    private Boolean bought;
    
    @OneToOne
    private PhotoEntity photoEntity;

    public ExtraServiceEntity() {
    }

    public ExtraServiceEntity(String id, String name, Double price, String description, Boolean register, Boolean bought, PhotoEntity photoEntity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.register = register;
        this.bought = bought;
        this.photoEntity = photoEntity;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoEntity getPhotoEntity() {
        return photoEntity;
    }

    public void setPhotoEntity(PhotoEntity photoEntity) {
        this.photoEntity = photoEntity;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    @Override
    public String toString() {
        return "ExtraServiceEntity{" + "id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", register=" + register + ", bought=" + bought + ", photoEntity=" + photoEntity + '}';
    }
    
}
