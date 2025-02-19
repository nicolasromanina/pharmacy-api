package com.example.narnia.pharmacy.repository;

import com.example.narnia.pharmacy.model.Sale;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {
    
    void deleteById(String id);

    Optional<Sale> findById(String id);
}
