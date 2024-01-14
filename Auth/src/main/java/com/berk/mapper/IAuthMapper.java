package com.berk.mapper;

import com.berk.dto.request.RegisterUserRequestDto;
import com.berk.dto.request.UpdateUserRequestDto;
import com.berk.dto.responce.UpdateUserResponseDto;
import com.berk.rabbitmq.model.CreateUserModel;
import com.berk.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);
    Auth toAuth (final RegisterUserRequestDto dto);
    Auth toUpdateAuth(final UpdateUserRequestDto dto);
    UpdateUserResponseDto toUserDetails(final Auth auth);
}
