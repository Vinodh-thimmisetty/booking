package com.vinodh.booking.api.repository;

import com.vinodh.booking.api.collections.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface JWTUserRepository extends MongoRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
