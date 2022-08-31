package com.taxi.taxi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxi.taxi.DTO.NewTravelDTO;
import com.taxi.taxi.DTO.TravelDTO;
import com.taxi.taxi.DTO.TravelTaxiDTO;
import com.taxi.taxi.services.TravelService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/taxis")
public class TravelController {
    final TravelService service;

    public TravelController(TravelService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @Secured({"ROLE_TRAVELER"})
    @PostMapping("/{id}/travels")
    public ResponseEntity<TravelDTO> create(@PathVariable("id") Long id, @Valid @RequestBody NewTravelDTO travelDTO){
        TravelDTO traveDTO = service.create(id, travelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(traveDTO);        
    }

    /* ================ RETRIEVE ================ */
    @Secured({"ROLE_TRAVELER"})
    @GetMapping("/{idTaxi}/travels/{id}")
    public ResponseEntity<TravelTaxiDTO> retrive(@PathVariable("idTaxi") Long idTaxi, @PathVariable("id") Long id){
        TravelTaxiDTO result = service.retrieve(idTaxi, id);
        return ResponseEntity.ok().body(result);        
    }
    
    /* ================ UPDATE ================ */
    @Secured({"ROLE_TRAVELER"})
    @PutMapping("/{idTaxi}/travels/{id}")
    public ResponseEntity<TravelTaxiDTO> update(@RequestBody TravelDTO travelDTO, @PathVariable("idTaxi") Long idTaxi, @PathVariable("id") Long id){
        TravelTaxiDTO result = service.update(travelDTO, idTaxi, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @Secured({"ROLE_TRAVELER"})
    @DeleteMapping("/{idTaxi}/travels/{id}")
    public ResponseEntity<Void> delete(@PathVariable("idTaxi") Long idTaxi, @PathVariable("id") Long id){
        service.delete(idTaxi, id);
        return ResponseEntity.noContent().build();
    }

    /* ================ COUNT ================ */
    @Secured({"ROLE_DRIVER","ROLE_TRAVELER"})
    @GetMapping("/{id}/travels/count")
    public ResponseEntity<Long> count(){
        long result = service.count();
        return ResponseEntity.ok().body(result);        
    }

    /* ================ LIST ================ */
    @Secured({"ROLE_DRIVER","ROLE_TRAVELER"})
    @GetMapping("/{id}/travels")
    public ResponseEntity<List<TravelDTO>> list(@PathVariable("id") Long id){
        List<TravelDTO> travels = service.list(id);
        return ResponseEntity.ok().body(travels);        
    }
}
