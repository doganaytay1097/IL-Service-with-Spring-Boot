package com.example.ilservice.repository;

import com.example.ilservice.model.Il;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ILRepository extends MongoRepository<Il,String> {
    List<Il> findAllByName(String name);
    Optional<Il> findByName(String name);
    Optional<Il> findByPlaka(int plaka);
}
