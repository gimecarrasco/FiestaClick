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
    public PartyEntity save( String idUser, CateringEntity cateringEntity, ExtraServiceEntity extraServiceEntity, EventRoomEntity eventRoomEntity, Date partyDate,String idEventRoom, String idCatering, String idExtraService) throws Exception {
                         
//        validate(idUser, cateringEntity, extraServiceEntity, eventRoomEntity, partyDate);
        
        UserEntity userEntity = userRepository.findById(idUser).get();
         
        PartyEntity partyEntity = new PartyEntity();
        partyEntity.setCateringEntity(cateringEntity);
        partyEntity.setEventRoomEntity(eventRoomEntity);
        partyEntity.setExtraServiceEntity((List<ExtraServiceEntity>) extraServiceEntity);
        partyEntity.setPartyDate(partyDate);
        partyEntity.setId(userEntity.getId());
        partyEntity.setTotal(totalPartyPrice(idEventRoom, idCatering, idExtraService)); 
               
        return partyRepository.save(partyEntity);
    }
    //usar con if
    public double totalPartyPrice(String idEventRoom, String idCatering, String idExtraService) {
        return (eventRoomRepository.findById(idEventRoom).get().getPrice() + cateringRepository.findById(idCatering).get().getPrice() + extraServiceRepository.findById(idExtraService).get().getPrice());
    }
    
    //Lleno con los caterings
    public String updateCatering(String idParty,String idCatering) throws ErrorService {
    	Optional<PartyEntity> response = partyRepository.findById(idParty);
        
    	if(response.isPresent()) {
    		PartyEntity entity = response.get();
                
    		Optional<CateringEntity> catResponse = cateringRepository.findById(idCatering);
    		if(catResponse.isPresent()) {
    			CateringEntity catEntity = catResponse.get();
    			entity.setCateringEntity(catEntity);
    			partyRepository.save(entity);
    			throw new ErrorService("Carrito Actualizado con Catering");

//                CateringEntity cE = new CateringEntity();
//                entity.setCateringEntity(cE);
//                partyRepository.save(entity);
                
////                throw new ErrorService("Carrito Actualizado con Catering");

    		}
    		throw new ErrorService( "Error al encontrar el catering");
    	}
    	throw new ErrorService("Error al encontrar al fiesta");
    }
    //hacer lo mismo con entretenimiento
    //hacer lo mismo con salon
    
    @Transactional
    public PartyEntity modify(String id, String idUser, CateringEntity cateringEntity, ExtraServiceEntity extraServiceEntity, EventRoomEntity eventRoomEntity, Date partyDate,String idEventRoom, String idCatering, String idExtraService) throws Exception{
        
        Optional<PartyEntity> answer = partyRepository.findById(id);
        
        validate(idUser, cateringEntity, extraServiceEntity, eventRoomEntity, partyDate);
        
        if (answer.isPresent()) {
            PartyEntity party = answer.get();
            
            party.getCateringEntity();
            party.getEventRoomEntity();
            party.getExtraServiceEntity();
            party.setPartyDate(partyDate);
            party.setTotal(totalPartyPrice(idEventRoom, idCatering, idExtraService));
            
            return partyRepository.save(party);

        } else {
            throw new ErrorService("No se encontro la fiesta que se desea modificar");
        }        
    }
    
    @Transactional
    public List<PartyEntity> listParty() {
        return partyRepository.findAll();
    }
       
    public PartyEntity findPartyByIdUser(String idUser) throws Exception{
    	Optional<PartyEntity> response = partyRepository.findPartyByUserId(idUser);
    	if(response.isPresent()) {
    		return response.get();
    	}else {
    		throw new Exception("Error al obtener lista de usuarios");
    	}
    }    
    
    @Transactional
    public Optional<PartyEntity> findPartyById(String id) {
        return partyRepository.findById(id);
    }
    
    @Transactional
    public void deleteById(String id) {
        partyRepository.deleteById(id);
    }
    
    public void validate(String idUser, CateringEntity cateringEntity, ExtraServiceEntity extraServiceEntity, EventRoomEntity eventRoomEntity, Date partyDate) throws Exception {
        if (idUser == null) {
            throw new ErrorService("No se encontró el usuario");
        }
        if (cateringEntity == null || extraServiceEntity == null || eventRoomEntity == null || partyDate == null) {
            throw new ErrorService("Debes elegir al menos un servicio.");
        }
    }
}
