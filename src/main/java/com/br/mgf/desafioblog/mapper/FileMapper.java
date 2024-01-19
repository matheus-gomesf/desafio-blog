package com.br.mgf.desafioblog.mapper;

import com.br.mgf.desafioblog.dto.FileDto;
import com.br.mgf.desafioblog.entity.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FileMapper {

    FileMapper FILE_MAPPER = Mappers.getMapper(FileMapper.class);

    @Mapping(target = "fileData", ignore = true)
    FileEntity dtoToEntity(FileDto record);

    FileDto entityToDto(FileEntity entity);
}
