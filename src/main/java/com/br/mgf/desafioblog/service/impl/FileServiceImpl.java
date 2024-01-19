package com.br.mgf.desafioblog.service.impl;

import com.br.mgf.desafioblog.config.StorageProperties;
import com.br.mgf.desafioblog.dto.FileDto;
import com.br.mgf.desafioblog.entity.AlbumEntity;
import com.br.mgf.desafioblog.entity.FileEntity;
import com.br.mgf.desafioblog.entity.PostEntity;
import com.br.mgf.desafioblog.repository.FileRepository;
import com.br.mgf.desafioblog.service.FileService;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.br.mgf.desafioblog.mapper.FileMapper.FILE_MAPPER;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
	private final StorageProperties storageProps;


	@Override
	public List<FileDto> saveFileWithPost(List<MultipartFile> files, PostEntity post) {
		List<FileDto> savedFiles = new ArrayList<>();
		for (MultipartFile file : files) {
			savedFiles.add(saveFile(file, post, null));
		}
		return savedFiles;
	}

	@Override
	public List<FileDto> saveFileWithAlbum(List<MultipartFile> files, AlbumEntity album) {
		List<FileDto> savedFiles = new ArrayList<>();
		for (MultipartFile file : files) {
			savedFiles.add(saveFile(file, null, album));
		}
		return savedFiles;
	}

	@Override
	public FileDto saveFile(MultipartFile file, PostEntity post, AlbumEntity album) {
		FileEntity fileEntity = new FileEntity();
		fileEntity.setPost(post);
		fileEntity.setAlbum(album);
		fileEntity.setFileName(file.getOriginalFilename());
		fileEntity.setFileType(file.getContentType());

		try {
			fileEntity.setFileData(file.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		FileEntity saved = fileRepository.save(fileEntity);

		uploadFile(file, saved);

		return FILE_MAPPER.entityToDto(fileEntity);
	}

	private void uploadFile(MultipartFile file, FileEntity saved) {
		try {
			String extension = Files.getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
			String fileName = saved.getId().toString().concat(".").concat(extension);
			file.transferTo(Paths.get(storageProps.getPath(), storageProps.getFolder(), fileName).normalize());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}