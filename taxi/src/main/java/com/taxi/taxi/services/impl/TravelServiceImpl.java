package com.taxi.taxi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.taxi.DTO.NewTravelDTO;
import com.taxi.taxi.DTO.TravelDTO;
import com.taxi.taxi.DTO.TravelTaxiDTO;
import com.taxi.taxi.exceptions.NoContentException;
import com.taxi.taxi.exceptions.ResourceNotFoundException;
import com.taxi.taxi.models.Taxi;
import com.taxi.taxi.models.Travel;
import com.taxi.taxi.repositories.TaxiRepository;
import com.taxi.taxi.repositories.TravelRepository;
import com.taxi.taxi.services.TravelService;

@Service
public class TravelServiceImpl implements TravelService{
    final ModelMapper modelMapper;
    final TravelRepository repository;
    final TaxiRepository taxiRepository;

    public TravelServiceImpl(TravelRepository r, TaxiRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.taxiRepository = er;
    }

    @Override
    @Transactional
    public TravelDTO create(Long idTaxi, NewTravelDTO travelDTO) {
        Taxi taxi = taxiRepository.findById(idTaxi)
            .orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));
        Travel travel = modelMapper.map(travelDTO, Travel.class);    
        travel.setTaxi(taxi);
        repository.save(travel);
        return modelMapper.map(travel, TravelDTO.class); 
    }

    @Override
    @Transactional(readOnly=true)
    public TravelTaxiDTO retrieve(Long idTaxi, Long id) {
        Taxi taxi = taxiRepository.findById(idTaxi)
            .orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));
        Travel travel = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        travel.setTaxi(taxi);
        return modelMapper.map(travel, TravelTaxiDTO.class);
    }

    @Override
    @Transactional
    public TravelTaxiDTO update(TravelDTO travelDTO, Long idTaxi, Long id) {
        Taxi taxi = taxiRepository.findById(idTaxi)
        .orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));
        Travel travel = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        travel = modelMapper.map(travelDTO, Travel.class);
        travel.setTaxi(taxi);
        repository.save(travel);       
        return modelMapper.map(travel, TravelTaxiDTO.class);
    }

    @Override
    public void delete(Long idTaxi, Long id) {
        Taxi taxi = taxiRepository.findById(idTaxi)
        .orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));
        Travel travel = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Travel not found"));
        travel.setTaxi(taxi);
        repository.deleteById(travel.getId()); 
        
    }
    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly=true)
    public List<TravelDTO> list(Long idTaxi) {
        Taxi taxi = taxiRepository.findById(idTaxi)
            .orElseThrow(()-> new ResourceNotFoundException("Taxi not found"));
        List<Travel> travels = repository.findByTaxi(taxi);
        if(travels.isEmpty()) throw new NoContentException("Travels is empty");
        //Lambda ->
        return travels.stream().map(q -> modelMapper.map(q, TravelDTO.class) )
            .collect(Collectors.toList());
    }
}
