package com.berk.mapper;

import com.berk.dto.request.GuestRentFootballFieldRequestDto;
import com.berk.dto.request.RentFootballFieldRequestDto;
import com.berk.dto.request.CancelRentFootballFieldRequestDto;
import com.berk.repository.entity.RentFootballField;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRentFootballFieldMapper {
    IRentFootballFieldMapper INSTANCE= Mappers.getMapper(IRentFootballFieldMapper.class);

    RentFootballField toRentFootballField(final RentFootballFieldRequestDto dto);
    RentFootballField toRentFootballFieldGuest(final GuestRentFootballFieldRequestDto dto);
    RentFootballField toRentFootballFieldCancel(final CancelRentFootballFieldRequestDto dto);

}
