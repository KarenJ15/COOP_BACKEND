package com.taxi.taxi.repositories;

// import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taxi.taxi.models.Client;
// import com.taxi.taxi.models.Travel;

public interface ClientRepository  extends JpaRepository<Client, Long>{
    public Optional<Client> findById(Long id);
}
