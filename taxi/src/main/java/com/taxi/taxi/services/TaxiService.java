package com.taxi.taxi.services;

import java.util.List;

import com.taxi.taxi.DTO.NewTaxiDTO;
import com.taxi.taxi.DTO.TaxiDTO;
import com.taxi.taxi.DTO.TaxiListDTO;

public interface TaxiService {
    public TaxiDTO create(NewTaxiDTO taxiDTO);
    public TaxiDTO retrieve(Long id);
    public TaxiDTO update(TaxiDTO taxiDTO, Long id);
    public void delete(Long id);
    public long count();

    public List<TaxiListDTO> list(int page, int size, String sort);
}
