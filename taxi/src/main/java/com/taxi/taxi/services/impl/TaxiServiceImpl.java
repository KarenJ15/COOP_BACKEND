package com.taxi.taxi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.taxi.taxi.DTO.NewTaxiDTO;
import com.taxi.taxi.DTO.TaxiDTO;
import com.taxi.taxi.DTO.TaxiListDTO;
import com.taxi.taxi.exceptions.NoContentException;
import com.taxi.taxi.exceptions.ResourceNotFoundException;
import com.taxi.taxi.models.Taxi;
import com.taxi.taxi.repositories.TaxiRepository;
import com.taxi.taxi.services.TaxiService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaxiServiceImpl implements TaxiService{
    final ModelMapper modelMapper;
    final TaxiRepository taxiRepository;

    public TaxiServiceImpl(TaxiRepository repository, ModelMapper mapper){
        this.taxiRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public TaxiDTO create(NewTaxiDTO taxiDTO) {
        Taxi taxi = modelMapper.map(taxiDTO, Taxi.class);
        taxiRepository.save(taxi);        
        return modelMapper.map(taxi, TaxiDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public TaxiDTO retrieve(Long id) {
        Taxi taxi = taxiRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));
        return modelMapper.map(taxi, TaxiDTO.class);
    }

    @Override
    @Transactional
    public TaxiDTO update(TaxiDTO taxiDTO, Long id) {
        Taxi taxi = taxiRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));        
              
        Taxi taxiUpdated = modelMapper.map(taxiDTO, Taxi.class);
        //Keeping values
        taxiUpdated.setCreatedBy(taxi.getCreatedBy());
        taxiUpdated.setCreatedDate(taxi.getCreatedDate());
        taxiRepository.save(taxiUpdated);   
        return modelMapper.map(taxiUpdated, TaxiDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Taxi taxi = taxiRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));        
        taxiRepository.deleteById(taxi.getId()); 
    }

    @Override
    public long count() {
        return taxiRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaxiListDTO> list(int page, int size, String sort) {
        Pageable pageable = sort == null || sort.isEmpty() ? 
                    PageRequest.of(page, size) 
                :   PageRequest.of(page, size,  Sort.by(sort));

        Page<Taxi> taxis = taxiRepository.findAll(pageable);
        if(taxis.isEmpty()) throw new NoContentException("Taxis is empty");
        return taxis.stream().map(taxi -> modelMapper.map(taxi, TaxiListDTO.class))
            .collect(Collectors.toList()); 
    }
    
}
