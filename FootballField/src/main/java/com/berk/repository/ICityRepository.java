package com.berk.repository;

import com.berk.repository.entity.City;
import com.berk.repository.entity.FootballField;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends MongoRepository<City,String> {
}
