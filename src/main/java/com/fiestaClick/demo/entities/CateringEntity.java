package com.fiestaClick.demo.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private Boolean register;

    @OneToMany
    private List<PhotoEntity> photoEntity;

    public CateringEntity() {
    }

    public CateringEntity(String id, String name, Integer price, String description, Boolean register, PhotoEntity photoEntity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.register = register;
        this.photoEntity = (List<PhotoEntity>) photoEntity;
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

    public List<PhotoEntity> getPhotoEntity() {
        return photoEntity;
    }

    public void setPhotoEntity(List<PhotoEntity> photoEntity) {
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
        return "CateringEntity{" + "id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", register=" + register + ", photoEntity=" + photoEntity + '}';
    }

}
