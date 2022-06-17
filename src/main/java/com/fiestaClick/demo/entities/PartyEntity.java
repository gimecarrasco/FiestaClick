package com.fiestaClick.demo.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author conta
 */
//@Data
@Entity
public class PartyEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    private EventRoomEntity eventRoomEntity;

    @OneToOne
    private CateringEntity cateringEntity;

    @OneToMany
    private List<ExtraServiceEntity> extraServiceEntity;

    @Temporal(TemporalType.DATE)
    private Date partyDate;

    private Double total;

    public PartyEntity() {
    }

    public PartyEntity(String id, EventRoomEntity eventRoomEntity, CateringEntity cateringEntity, List<ExtraServiceEntity> extraServiceEntity, Date partyDate, Double total) {
        this.id = id;
        this.eventRoomEntity = eventRoomEntity;
        this.cateringEntity = cateringEntity;
        this.extraServiceEntity = extraServiceEntity;
        this.partyDate = partyDate;
        this.total = total;
    }
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventRoomEntity getEventRoomEntity() {
        return eventRoomEntity;
    }

    public void setEventRoomEntity(EventRoomEntity eventRoomEntity) {
        this.eventRoomEntity = eventRoomEntity;
    }

    public CateringEntity getCateringEntity() {
        return cateringEntity;
    }

    public void setCateringEntity(CateringEntity cateringEntity) {
        this.cateringEntity = cateringEntity;
    }

    public List<ExtraServiceEntity> getExtraServiceEntity() {
        return extraServiceEntity;
    }

    public void setExtraServiceEntity(List<ExtraServiceEntity> extraServiceEntity) {
        this.extraServiceEntity = extraServiceEntity;
    }

    public Date getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(Date partyDate) {
        this.partyDate = partyDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }    
  

}

