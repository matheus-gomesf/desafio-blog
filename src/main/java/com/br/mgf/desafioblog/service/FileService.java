package com.br.mgf.desafioblog.service;

import com.br.mgf.desafioblog.dto.FileDto;
import com.br.mgf.desafioblog.entity.AlbumEntity;
import com.br.mgf.desafioblog.entity.PostEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

	List<FileDto> saveFileWithPost(List<MultipartFile> files, PostEntity toSave);

	List<FileDto> saveFileWithAlbum(List<MultipartFile> files, AlbumEntity toSave);

	FileDto saveFile(MultipartFile file, PostEntity post, AlbumEntity album);
}
