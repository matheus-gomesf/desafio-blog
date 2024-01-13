package com.br.mgf.desafioblog.repository;

import com.br.mgf.desafioblog.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
}
