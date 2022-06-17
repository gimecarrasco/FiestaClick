package com.fiestaClick.demo.service;

import com.fiestaClick.demo.entities.CateringEntity;
import com.fiestaClick.demo.entities.EventRoomEntity;
import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.entities.PartyEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.entities.UserEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.CateringRepository;
import com.fiestaClick.demo.repository.EventRoomRepository;
import com.fiestaClick.demo.repository.ExtraServiceRepository;
import com.fiestaClick.demo.repository.PartyRepository;
import com.fiestaClick.demo.repository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author conta
 */
@Service
public class PartyService {    
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PartyRepository partyRepository;
    
    @Autowired
    private EventRoomRepository eventRoomRepository;
    
    @Autowired
    private CateringRepository cateringRepository;
    
    @Autowired
    private ExtraServiceRepository extraServiceRepository;
    
    
    @Transactional
    public PartyEntity save( String idUser, CateringEntity cateringEntity, ExtraServiceEntity extraServiceEntity, EventRoomEntity eventRoomEntity) throws Exception {
        
                 
//        validate(idUser, cateringEntity, extraServiceEntity, eventRoomEntity);
        
        UserEntity userEntity = userRepository.findByUserID(idUser);
         
        PartyEntity partyEntity = new PartyEntity();
        partyEntity.setCateringEntity(cateringEntity);
        partyEntity.setEventRoomEntity(eventRoomEntity);
        partyEntity.setExtraServiceEntity((List<ExtraServiceEntity>) extraServiceEntity);
        partyEntity.setPartyDate(new Date());
        partyEntity.setUserEntity(userEntity);
        partyEntity.setTotal(0.0); 
               
        return partyRepository.save(partyEntity);
    }
    
    public double totalPartyPrice(String idEventRoom, String idCatering, String idExtraService) {
        return (eventRoomRepository.findById(idEventRoom).get().getPrice() + cateringRepository.findById(idCatering).get().getPrice() + extraServiceRepository.findById(idExtraService).get().getPrice());
    }
    
    @Transactional
    public PartyEntity modify(String id, String idUser, CateringEntity cateringEntity, ExtraServiceEntity extraServiceEntity, EventRoomEntity eventRoomEntity) throws Exception{
        
        Optional<PartyEntity> answer = partyRepository.findById(id);
        
        validate(idUser, cateringEntity, extraServiceEntity, eventRoomEntity);
        
        if (answer.isPresent()) {
            PartyEntity party = answer.get();
            
            party.getCateringEntity();
            party.getEventRoomEntity();
            party.getExtraServiceEntity();
            party.setTotal(0.0);
            
            return partyRepository.save(party);

        } else {
            throw new ErrorService("No se encontro la fiesta que se desea modificar");
        }        
    }
    
    @Transactional
    public List<PartyEntity> listParty() {
        return partyRepository.findAll();
    }
       
    @Transactional
    public Optional<PartyEntity> findPartyById(String id) {
        return partyRepository.findById(id);
    }
    
    @Transactional
    public void deleteById(String id) {
        partyRepository.deleteById(id);
    }
    
    public void validate(String idUser, CateringEntity cateringEntity, ExtraServiceEntity extraServiceEntity, EventRoomEntity eventRoomEntity) throws Exception {
        if (idUser == null) {
            throw new ErrorService("No se encontró el usuario");
        }
        if (cateringEntity == null || extraServiceEntity == null || eventRoomEntity == null) {
            throw new ErrorService("Debes elegir al menos un servicio.");
        }
    }
}
