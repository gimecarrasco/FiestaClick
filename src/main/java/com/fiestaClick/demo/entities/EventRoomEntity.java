package com.fiestaClick.demo.entities;

import com.fiestaClick.demo.enumerations.City;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class EventRoomEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;

    private String name;
    private Double price;
    private String description;
    private Boolean register;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToOne
    private PhotoEntity photoEntity;

    public EventRoomEntity() {
    }

    public EventRoomEntity(String Id, String name, Double price, String description, Boolean register, Date date, PhotoEntity photoEntity) {
        this.Id = Id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.register = register;
        this.date = date;
        this.photoEntity = photoEntity;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PhotoEntity getPhotoEntity() {
        return photoEntity;
    }

    public void setPhotoEntity(PhotoEntity photoEntity) {
        this.photoEntity = photoEntity;
    }

    @Override
    public String toString() {
        return "EventRoomEntity{" + "Id=" + Id + ", name=" + name + ", price=" + price + ", description=" + description + ", register=" + register + ", date=" + date + ", photoEntity=" + photoEntity + '}';
    }



}
