package com.taxi.taxi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.taxi.DTO.ClientDTO;
import com.taxi.taxi.DTO.ClientListDTO;
import com.taxi.taxi.DTO.NewClientDTO;
import com.taxi.taxi.exceptions.NoContentException;
import com.taxi.taxi.exceptions.ResourceNotFoundException;
import com.taxi.taxi.models.Client;
import com.taxi.taxi.repositories.ClientRepository;
import com.taxi.taxi.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
    final ModelMapper modelMapper;
    final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository r, ModelMapper m)
    {
        this.modelMapper = m;
        this.clientRepository = r;
    }

    @Override
    @Transactional
    public ClientDTO create(NewClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        clientRepository.save(client);        
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Client not found"));        
        clientRepository.deleteById(client.getId()); 
        
    }

    @Override
    public long count() {
        return clientRepository.count();
    }

    @Override
    public List<ClientListDTO> list(int page, int size, String sort) {
        Pageable pageable = sort == null || sort.isEmpty() ? 
                    PageRequest.of(page, size) 
                :   PageRequest.of(page, size,  Sort.by(sort));

        Page<Client> clients = clientRepository.findAll(pageable);
        if(clients.isEmpty()) throw new NoContentException("Client is empty");
        return clients.stream().map(client -> modelMapper.map(client, ClientListDTO.class))
            .collect(Collectors.toList());
    }
    
}
