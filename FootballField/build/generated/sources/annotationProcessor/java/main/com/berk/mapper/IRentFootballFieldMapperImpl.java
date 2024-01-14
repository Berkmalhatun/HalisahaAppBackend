package com.berk.mapper;

import com.berk.dto.request.CancelRentFootballFieldRequestDto;
import com.berk.dto.request.GuestRentFootballFieldRequestDto;
import com.berk.dto.request.RentFootballFieldRequestDto;
import com.berk.repository.entity.RentFootballField;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-20T15:27:32+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (JetBrains s.r.o.)"
)
@Component
public class IRentFootballFieldMapperImpl implements IRentFootballFieldMapper {

    @Override
    public RentFootballField toRentFootballField(RentFootballFieldRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        RentFootballField.RentFootballFieldBuilder<?, ?> rentFootballField = RentFootballField.builder();

        rentFootballField.footballFieldid( dto.getFootballFieldid() );
        rentFootballField.userid( dto.getUserid() );
        rentFootballField.startDate( dto.getStartDate() );
        rentFootballField.endDate( dto.getEndDate() );
        rentFootballField.time( dto.getTime() );

        return rentFootballField.build();
    }

    @Override
    public RentFootballField toRentFootballFieldGuest(GuestRentFootballFieldRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        RentFootballField.RentFootballFieldBuilder<?, ?> rentFootballField = RentFootballField.builder();

        rentFootballField.footballFieldid( dto.getFootballFieldid() );
        rentFootballField.startDate( dto.getStartDate() );
        rentFootballField.endDate( dto.getEndDate() );
        rentFootballField.time( dto.getTime() );
        rentFootballField.name( dto.getName() );
        rentFootballField.surname( dto.getSurname() );
        rentFootballField.telNo( dto.getTelNo() );
        rentFootballField.email( dto.getEmail() );

        return rentFootballField.build();
    }

    @Override
    public RentFootballField toRentFootballFieldCancel(CancelRentFootballFieldRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        RentFootballField.RentFootballFieldBuilder<?, ?> rentFootballField = RentFootballField.builder();

        rentFootballField.id( dto.getId() );

        return rentFootballField.build();
    }
}
