package com.br.mgf.desafioblog.service.impl;

import com.br.mgf.desafioblog.entity.FileEntity;
import com.br.mgf.desafioblog.repository.FileRepository;
import com.br.mgf.desafioblog.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public FileEntity saveFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFileData(file.getBytes());
        return fileRepository.save(fileEntity);
    }
}