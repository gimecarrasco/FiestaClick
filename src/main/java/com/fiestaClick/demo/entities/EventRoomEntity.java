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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class EventRoomEntity {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;
    private Integer capacity;
    private String adress;
    
    @Enumerated(EnumType.STRING)
    private City city;
    private String name;
    private Boolean register;
    private String description;
    private String decor;
    
    @Temporal(TemporalType.DATE)
    private Date date;
   @OneToMany  
    private List <PhotoEntity> photoEntity;
    private Double price;
    

    public EventRoomEntity() {
    }

    public EventRoomEntity(String Id, Integer capacity, String adress, City city, String name, Boolean register, String description, String decor, Date date, List<PhotoEntity> photoEntity, Double price) {
        this.Id = Id;
        this.capacity = capacity;
        this.adress = adress;
        this.city = city;
        this.name = name;
        this.register = register;
        this.description = description;
        this.decor = decor;
        this.date = date;
        this.photoEntity = photoEntity;
        this.price = price;
    }



    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDecor() {
        return decor;
    }

    public void setDecor(String decor) {
        this.decor = decor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<PhotoEntity> getPhotoEntity() {
        return photoEntity;
    }

    public void setPhotoEntity(List<PhotoEntity> photoEntity) {
        this.photoEntity = photoEntity;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "EventRoomEntity{" + "Id=" + Id + ", capacity=" + capacity + ", adress=" + adress + ", city=" + city + ", name=" + name + ", register=" + register + ", description=" + description + ", decor=" + decor + ", date=" + date + ", photoEntity=" + photoEntity + ", price=" + price + '}';
    }
    
    
}
