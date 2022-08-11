package com.taxi.taxi.services;

import java.util.List;

import com.taxi.taxi.DTO.ClientDTO;
import com.taxi.taxi.DTO.ClientListDTO;
import com.taxi.taxi.DTO.NewClientDTO;

public interface ClientService {
    public ClientDTO create(NewClientDTO clientDTO);
    public void delete(Long id);
    public long count();

    public List<ClientListDTO> list(int page, int size, String sort);
}
