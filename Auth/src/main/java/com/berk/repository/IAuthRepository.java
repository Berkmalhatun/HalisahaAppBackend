package com.berk.repository;

import com.berk.repository.entity.Auth;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends MongoRepository<Auth, String> {
    Optional<Auth> findByEmail(String email);
    Optional<Auth> findByEmailAndPassword(String email,String password);
}
