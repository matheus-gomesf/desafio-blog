package com.br.mgf.desafioblog.mapper;

import com.br.mgf.desafioblog.dto.AlbumDto;
import com.br.mgf.desafioblog.entity.AlbumEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AlbumMapper {

    AlbumMapper ALBUM_MAPPER = Mappers.getMapper(AlbumMapper.class);

    @Mapping(source = "authorDto", target = "author")
    AlbumEntity dtoToEntity(AlbumDto record);

    @InheritInverseConfiguration
    AlbumDto entityToDto(AlbumEntity entity);
}
