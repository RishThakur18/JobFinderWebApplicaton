package com.jobify.microservices.entities.mappers;

import com.jobify.microservices.entities.dtos.UserDto;
import com.jobify.microservices.entities.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    UserDto modelToDto(User user);
    User dtoToModel(UserDto userDto);
}
