package com.br.mgf.desafioblog.service.impl;

import com.br.mgf.desafioblog.dto.AlbumDto;
import com.br.mgf.desafioblog.dto.FileDto;
import com.br.mgf.desafioblog.dto.UserDto;
import com.br.mgf.desafioblog.entity.AlbumEntity;
import com.br.mgf.desafioblog.entity.FileEntity;
import com.br.mgf.desafioblog.exception.InvalidDeletionException;
import com.br.mgf.desafioblog.exception.ResourceNotFoundException;
import com.br.mgf.desafioblog.mapper.AlbumMapper;
import com.br.mgf.desafioblog.repository.AlbumRepository;
import com.br.mgf.desafioblog.service.AlbumService;
import com.br.mgf.desafioblog.service.FileService;
import com.br.mgf.desafioblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.br.mgf.desafioblog.mapper.FileMapper.FILE_MAPPER;
import static com.br.mgf.desafioblog.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

	private final AlbumRepository albumRepository;
	private final UserService userService;
	private final FileService fileService;
	private final AlbumMapper albumMapper;

	@Override
	public AlbumDto createAlbum(List<MultipartFile> files, Authentication authentication) {

		AlbumEntity toSave = new AlbumEntity();

		UserDto userDto = userService.getUserByEmail(authentication.getName());
		toSave.setAuthor(USER_MAPPER.dtoToEntity(userDto));

		List<FileDto> fileDtos = fileService.saveFileWithAlbum(files, toSave);
		List<FileEntity> images = new ArrayList<>(fileDtos.stream().map(FILE_MAPPER::dtoToEntity).toList());
		toSave.setFiles(images);

		AlbumEntity saved = albumRepository.save(toSave);

		return albumMapper.entityToDto(saved);
	}

	@Override
	public Page<AlbumDto> listAlbum(Pageable pageable) {
		return albumRepository.findAll(pageable).map(albumMapper::entityToDto);
	}

	@Override
	public void deleteAlbum(UUID id, Authentication authentication) {
		UserDto userDto = userService.getUserByEmail(authentication.getName());
		AlbumEntity album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album", "id", id.toString()));

		if (!album.getAuthor().getId().equals(userDto.getId())) {
			throw new InvalidDeletionException("album");
		}

		albumRepository.deleteById(id);
	}
}
