package com.example.narnia.pharmacy.repository;

import com.example.narnia.pharmacy.model.Customer;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findById(String id);
    
    void deleteById(String id);
}
