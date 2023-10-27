package io.pessoas_java.config.security.converter;

import io.pessoas_java.adapters.out.entity.UserEntity;
import io.pessoas_java.application.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);
}

