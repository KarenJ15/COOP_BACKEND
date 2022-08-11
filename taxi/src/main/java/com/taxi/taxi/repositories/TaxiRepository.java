package com.taxi.taxi.repositories;

//import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxi.taxi.models.Taxi;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long>{
    public Optional<Taxi> findById(Long id);
}
