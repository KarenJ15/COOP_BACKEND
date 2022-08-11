package com.taxi.taxi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taxi.taxi.DTO.NewTaxiDTO;
import com.taxi.taxi.DTO.TaxiDTO;
import com.taxi.taxi.DTO.TaxiListDTO;
import com.taxi.taxi.services.TaxiService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/taxis")
public class TaxiController {
    private final TaxiService service;
  
    public TaxiController(TaxiService srv){
        this.service =srv;
    }
    
    /* ================ CREATE ================ */
    @PostMapping()
    public ResponseEntity<TaxiDTO> create(@Valid @RequestBody NewTaxiDTO taxiDTO){
        TaxiDTO result = service.create(taxiDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);        
    }

    /* ================ RETRIEVE ================ */
    @GetMapping("/{id}")
    public ResponseEntity<TaxiDTO> retrive(@PathVariable("id") Long id){
        TaxiDTO result = service.retrieve(id);
        return ResponseEntity.ok().body(result);        
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{id}")
    public ResponseEntity<TaxiDTO> update(@RequestBody TaxiDTO taxiDTO, @PathVariable("id") Long id){
        TaxiDTO result = service.update(taxiDTO, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* ================ COUNT ================ */
     @GetMapping("/count")
     public ResponseEntity<Long> count(){
         long result = service.count();
         return ResponseEntity.ok().body(result);        
     }

    /* ================ LIST ================ */
    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<TaxiListDTO>> list(@PathVariable("page") int page, 
        @PathVariable("size") int size,
        @RequestParam(name = "sort", required = false) String sort){
        List<TaxiListDTO> result  = service.list(page, size, sort);
        return ResponseEntity.ok().body(result);        
    }

}
