package com.br.mgf.desafioblog.mapper;

import com.br.mgf.desafioblog.dto.CommentDto;
import com.br.mgf.desafioblog.dto.FileDto;
import com.br.mgf.desafioblog.entity.CommentEntity;
import com.br.mgf.desafioblog.entity.FileEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FileMapper {

    FileMapper FILE_MAPPER = Mappers.getMapper(FileMapper.class);

    @Mapping(source = "postDto", target = "post")
    FileEntity dtoToEntity(FileDto record);

    @InheritInverseConfiguration
    FileDto entityToDto(FileEntity entity);
}
