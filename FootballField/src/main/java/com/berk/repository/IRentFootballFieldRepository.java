package com.berk.repository;

import com.berk.repository.entity.FootballField;
import com.berk.repository.entity.RentFootballField;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IRentFootballFieldRepository extends MongoRepository<RentFootballField,String> {
    List<RentFootballField> findByFootballFieldidAndStartDateAndEndDate(String footballFieldId,Date startDate,Date endDate);
    List<RentFootballField> findByUserid(String userid, Sort sort);
}
