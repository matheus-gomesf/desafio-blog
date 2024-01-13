package com.br.mgf.desafioblog.mapper;

import com.br.mgf.desafioblog.dto.PostDto;
import com.br.mgf.desafioblog.entity.PostEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PostMapper {

    PostMapper POST_MAPPER = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "authorDto", target = "author")
    PostEntity dtoToEntity(PostDto record);

    @InheritInverseConfiguration(name = "dtoToEntity")
    PostDto entityToDto(PostEntity entity);

    void updateFromDto(PostDto dto, @MappingTarget PostEntity entity);
}
