package com.br.mgf.desafioblog.repository;

import com.br.mgf.desafioblog.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
}
