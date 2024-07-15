package com.aluracursos.forohub.mapper;


import com.aluracursos.forohub.dto.UserDto;
import com.aluracursos.forohub.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "Spring")
public interface UserMapperInterface{

    UserMapperInterface mapper = Mappers.getMapper(UserMapperInterface.class);
    User toEntity(UserDto userDto);

    UserDto toDto(User user);


}
