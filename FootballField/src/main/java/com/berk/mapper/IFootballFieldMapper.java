package com.berk.mapper;

import com.berk.dto.request.UpdateFootballFieldRequestDto;
import com.berk.rabbitmq.model.RegisterFootballFieldModel;
import com.berk.repository.entity.FootballField;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.mongodb.core.query.Update;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IFootballFieldMapper {
    IFootballFieldMapper INSTANCE= Mappers.getMapper(IFootballFieldMapper.class);

    FootballField toFootballField(final RegisterFootballFieldModel model);
    FootballField updateFootballField(final UpdateFootballFieldRequestDto dto);
}
