package com.berk.repository;

import com.berk.repository.entity.FootballField;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFootballFieldRepository extends MongoRepository<FootballField,String> {
Optional<FootballField> findByEmail(String email);
List<FootballField> findByCityAndDistrict(String city, String district);
List<FootballField> findByUserid(String userid);
}
