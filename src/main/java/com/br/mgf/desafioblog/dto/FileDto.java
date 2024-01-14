package com.br.mgf.desafioblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FileDto {

	private UUID id;

	private String fileName;

	private String fileType;

	private byte[] fileData;

	private AlbumDto album;

	private Date createdAt;

	private PostDto post;
}