package com.br.mgf.desafioblog.mapper;

import com.br.mgf.desafioblog.dto.CommentDto;
import com.br.mgf.desafioblog.entity.CommentEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CommentMapper {

    CommentMapper COMMENT_MAPPER = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "authorDto", target = "author")
    @Mapping(source = "postDto", target = "post")
    CommentEntity dtoToEntity(CommentDto record);

    @InheritInverseConfiguration(name = "dtoToEntity")
    CommentDto entityToDto(CommentEntity entity);
}
