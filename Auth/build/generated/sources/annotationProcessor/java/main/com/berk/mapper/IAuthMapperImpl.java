package com.berk.mapper;

import com.berk.dto.request.RegisterUserRequestDto;
import com.berk.dto.request.UpdateUserRequestDto;
import com.berk.dto.responce.UpdateUserResponseDto;
import com.berk.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-27T13:51:52+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (JetBrains s.r.o.)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public Auth toAuth(RegisterUserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.name( dto.getName() );
        auth.surname( dto.getSurname() );
        auth.email( dto.getEmail() );
        auth.telNo( dto.getTelNo() );
        auth.password( dto.getPassword() );
        auth.role( dto.getRole() );

        return auth.build();
    }

    @Override
    public Auth toUpdateAuth(UpdateUserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.id( dto.getId() );
        auth.name( dto.getName() );
        auth.surname( dto.getSurname() );
        auth.email( dto.getEmail() );
        auth.telNo( dto.getTelNo() );
        auth.role( dto.getRole() );

        return auth.build();
    }

    @Override
    public UpdateUserResponseDto toUserDetails(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        UpdateUserResponseDto.UpdateUserResponseDtoBuilder updateUserResponseDto = UpdateUserResponseDto.builder();

        updateUserResponseDto.name( auth.getName() );
        updateUserResponseDto.surname( auth.getSurname() );
        updateUserResponseDto.email( auth.getEmail() );
        updateUserResponseDto.telNo( auth.getTelNo() );

        return updateUserResponseDto.build();
    }
}
