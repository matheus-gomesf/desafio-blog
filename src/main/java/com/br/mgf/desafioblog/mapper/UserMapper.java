package com.br.mgf.desafioblog.mapper;

import com.br.mgf.desafioblog.dto.UserDto;
import com.br.mgf.desafioblog.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserEntity dtoToEntity(UserDto record);

    UserDto entityToDto(UserEntity entity);

    void updateFromDto(UserDto dto, @MappingTarget UserEntity entity);

}
