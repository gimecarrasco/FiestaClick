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

    @ManyToOne
    private UserEntity userEntity;
    
    @Temporal(TemporalType.DATE)
    private Date partyDate;

    private Integer total;

    public PartyEntity() {
    }

    public PartyEntity(EventRoomEntity eventRoomEntity, CateringEntity cateringEntity, List<ExtraServiceEntity> extraServiceEntity, UserEntity userEntity, Date partyDate, Integer total) {
        this.eventRoomEntity = eventRoomEntity;
        this.cateringEntity = cateringEntity;
        this.extraServiceEntity = extraServiceEntity;
        this.userEntity = userEntity;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Date getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(Date partyDate) {
        this.partyDate = partyDate;
    }
    
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PartyEntity{" + "id=" + id + ", eventRoomEntity=" + eventRoomEntity + ", cateringEntity=" + cateringEntity + ", extraServiceEntity=" + extraServiceEntity + ", userEntity=" + userEntity + ", partyDate=" + partyDate + ", total=" + total + '}';
    }

}

