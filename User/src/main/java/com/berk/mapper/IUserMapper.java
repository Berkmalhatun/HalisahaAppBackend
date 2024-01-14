package com.berk.mapper;

import com.berk.rabbitmq.model.CreateUserModel;
import com.berk.rabbitmq.model.UpdateUserPasswordModel;
import com.berk.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);
    @Mapping(source = "id",target = "authid")
    User toUser(final CreateUserModel model);
    @Mapping(source = "id",target = "authid")
    User toUpdateUser(final UpdateUserPasswordModel model);
}
