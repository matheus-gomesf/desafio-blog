package com.br.mgf.desafioblog.repository;

import com.br.mgf.desafioblog.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository<AlbumEntity, UUID> {
}