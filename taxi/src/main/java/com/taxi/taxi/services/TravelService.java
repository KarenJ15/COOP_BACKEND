package com.taxi.taxi.services;

import java.util.List;

import com.taxi.taxi.DTO.NewTravelDTO;
import com.taxi.taxi.DTO.TravelDTO;
import com.taxi.taxi.DTO.TravelTaxiDTO;


public interface TravelService {
    public TravelDTO create(Long idTaxi, NewTravelDTO travelDTO);
    public TravelTaxiDTO retrieve(Long idTaxi, Long id);
    public TravelTaxiDTO update(TravelDTO travelDTO, Long idTaxi, Long id);
    public void delete(Long idTaxi, Long id);
    public long count();

    public List<TravelDTO> list(Long idTaxi);
}
