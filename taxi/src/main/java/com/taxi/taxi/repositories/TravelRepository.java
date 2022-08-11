package com.taxi.taxi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taxi.taxi.models.Taxi;
import com.taxi.taxi.models.Travel;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    public List<Travel> findByTaxi(Taxi taxi);
    
}
