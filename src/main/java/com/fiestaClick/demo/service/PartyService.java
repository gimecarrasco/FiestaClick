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
    public PartyEntity save(CateringEntity cateringEntity, ExtraServiceEntity extraServiceEntity, EventRoomEntity eventRoomEntity) throws Exception {
        
                 
        validate(cateringEntity, extraServiceEntity, eventRoomEntity);
         
        PartyEntity partyEntity = new PartyEntity();
        partyEntity.setCateringEntity(cateringEntity);
        partyEntity.setEventRoomEntity(eventRoomEntity);
        partyEntity.setExtraServiceEntity(extraServiceEntity);
//        partyEntity.setPartyDate(partyDate);
        partyEntity.setTotalPrice(totalPartyPrice(eventRoomEntity.getId(), cateringEntity.getId(), extraServiceEntity.getId())); 
               
        return partyRepository.save(partyEntity);
    }
    
    public Double totalPartyPrice(String idEventRoom, String idCatering, String idExtraService) {
        Double priceEventRoom = 0.0;
        Double priceCatering = 0.0;
        Double priceExtraService = 0.0;
        
        if (eventRoomRepository.findById(idEventRoom).get().getPrice() != null) {
            priceEventRoom = eventRoomRepository.findById(idEventRoom).get().getPrice();
        }
        
         if (cateringRepository.findById(idCatering).get().getPrice() != null) {
            priceCatering = cateringRepository.findById(idCatering).get().getPrice();
        }
         
          if (extraServiceRepository.findById(idExtraService).get().getPrice() != null) {
            priceExtraService = extraServiceRepository.findById(idExtraService).get().getPrice();
        }
        return (priceEventRoom + priceCatering + priceExtraService);
    }
    
    @Transactional
    public PartyEntity modify(String id, String idUser, CateringEntity cateringEntity, ExtraServiceEntity extraServiceEntity, EventRoomEntity eventRoomEntity, Date partyDate,String idEventRoom, String idCatering, String idExtraService) throws Exception{
        
        Optional<PartyEntity> answer = partyRepository.findById(id);
        
        validate(cateringEntity, extraServiceEntity, eventRoomEntity);
        
        if (answer.isPresent()) {
            PartyEntity party = answer.get();
            
            party.getCateringEntity();
            party.getEventRoomEntity();
            party.getExtraServiceEntity();
            party.setPartyDate(partyDate);
//            party.setTotal(totalPartyPrice(idEventRoom, idCatering, idExtraService));
            
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
    
    public void validate(CateringEntity cateringEntity, ExtraServiceEntity extraServiceEntity, EventRoomEntity eventRoomEntity) throws Exception {
        if (cateringEntity == null || extraServiceEntity == null || eventRoomEntity == null) {
            throw new ErrorService("Debes elegir al menos un servicio.");
        }
    }
}
