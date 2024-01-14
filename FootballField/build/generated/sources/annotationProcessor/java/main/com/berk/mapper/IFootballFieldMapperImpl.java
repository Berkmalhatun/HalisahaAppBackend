package com.berk.mapper;

import com.berk.dto.request.UpdateFootballFieldRequestDto;
import com.berk.rabbitmq.model.RegisterFootballFieldModel;
import com.berk.repository.entity.FootballField;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-11T02:02:44+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (JetBrains s.r.o.)"
)
@Component
public class IFootballFieldMapperImpl implements IFootballFieldMapper {

    @Override
    public FootballField toFootballField(RegisterFootballFieldModel model) {
        if ( model == null ) {
            return null;
        }

        FootballField.FootballFieldBuilder<?, ?> footballField = FootballField.builder();

        footballField.userid( model.getUserid() );
        footballField.name( model.getName() );
        footballField.telephoneNumber( model.getTelephoneNumber() );
        footballField.city( model.getCity() );
        footballField.district( model.getDistrict() );
        footballField.address( model.getAddress() );
        footballField.price( model.getPrice() );
        footballField.email( model.getEmail() );

        return footballField.build();
    }

    @Override
    public FootballField updateFootballField(UpdateFootballFieldRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        FootballField.FootballFieldBuilder<?, ?> footballField = FootballField.builder();

        footballField.id( dto.getId() );
        footballField.name( dto.getName() );
        footballField.telephoneNumber( dto.getTelephoneNumber() );
        footballField.city( dto.getCity() );
        footballField.district( dto.getDistrict() );
        footballField.address( dto.getAddress() );
        footballField.price( dto.getPrice() );
        footballField.email( dto.getEmail() );

        return footballField.build();
    }
}
