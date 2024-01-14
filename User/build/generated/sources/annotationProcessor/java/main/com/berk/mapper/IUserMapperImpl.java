package com.berk.mapper;

import com.berk.rabbitmq.model.CreateUserModel;
import com.berk.rabbitmq.model.UpdateUserPasswordModel;
import com.berk.repository.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-12T15:52:53+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (JetBrains s.r.o.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User toUser(CreateUserModel model) {
        if ( model == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.authid( model.getId() );
        user.id( model.getId() );
        user.name( model.getName() );
        user.surname( model.getSurname() );
        user.email( model.getEmail() );
        user.telNo( model.getTelNo() );
        user.password( model.getPassword() );
        user.status( model.getStatus() );
        user.role( model.getRole() );

        return user.build();
    }

    @Override
    public User toUpdateUser(UpdateUserPasswordModel model) {
        if ( model == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.authid( model.getId() );
        user.id( model.getId() );
        user.email( model.getEmail() );
        user.password( model.getPassword() );

        return user.build();
    }
}
