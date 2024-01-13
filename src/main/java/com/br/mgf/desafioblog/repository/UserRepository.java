package com.br.mgf.desafioblog.repository;

import com.br.mgf.desafioblog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    Optional<UserDetails> findByEmail(String email);
}