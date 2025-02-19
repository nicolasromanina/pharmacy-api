package com.example.narnia.auth.repository;

import com.example.narnia.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    
    // Check if a user already exists by email
    boolean existsByEmail(String email);
}
