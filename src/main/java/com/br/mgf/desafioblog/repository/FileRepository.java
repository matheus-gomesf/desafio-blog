package com.br.mgf.desafioblog.repository;

import com.br.mgf.desafioblog.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {
}