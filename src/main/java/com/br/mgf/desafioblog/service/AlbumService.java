package com.br.mgf.desafioblog.service;

import com.br.mgf.desafioblog.dto.AlbumDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface AlbumService {

	AlbumDto createAlbum(List<MultipartFile> files, Authentication authentication);

	Page<AlbumDto> listAlbum(Pageable pageable);

	void deleteAlbum(UUID id, Authentication authentication);
}
