package com.fiestaClick.demo.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author conta
 */
@Data
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
    private ExtraServiceEntity extraServiceEntity;
    
    @OneToMany
    private UserEntity userEntity;
    
    @OneToOne
    @Temporal(TemporalType.DATE)
    private Date create;
    
    @OneToOne
    private Integer total;
    
            
    
}
