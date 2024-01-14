package com.br.mgf.desafioblog.service;

import com.br.mgf.desafioblog.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
	public FileEntity saveFile(MultipartFile file) throws IOException;
}
