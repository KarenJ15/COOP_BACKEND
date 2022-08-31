package com.taxi.taxi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxi.taxi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {   
    public User findByName(String name);
}
