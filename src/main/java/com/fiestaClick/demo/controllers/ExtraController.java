
package com.fiestaClick.demo.controllers;


import com.fiestaClick.demo.entities.ExtraServiceEntity;
import com.fiestaClick.demo.entities.PhotoEntity;
import com.fiestaClick.demo.errors.ErrorService;
import com.fiestaClick.demo.repository.ExtraServiceRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Controller
@RequestMapping("/extra")
public class ExtraController {
    
    @Autowired
    private ExtraService extraService;

}
