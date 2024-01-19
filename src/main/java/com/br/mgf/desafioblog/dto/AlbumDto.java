package com.br.mgf.desafioblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AlbumDto {

	private UUID id;

	private String name;

	private List<FileDto> files;

	private Date createdAt;

	private UserDto authorDto;
}