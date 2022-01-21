package com.jobify.microservices.entities.mappers;

import com.jobify.microservices.entities.dtos.UserDto;
import com.jobify.microservices.entities.enums.UserRole;
import com.jobify.microservices.entities.models.User;
import io.github.toolfactory.jvm.function.util.Strings;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto modelToDto(User user);
    User dtoToModel(UserDto userDto);

    default String checkEmpty(String string) {
        if(StringUtils.isEmpty(string))
            return null;
        return string;
    }

    default List<UserRole> checkEmpty(List<UserRole> list) {;
        return list;
    }

}

