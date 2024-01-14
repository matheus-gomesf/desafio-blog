package com.br.mgf.desafioblog.service;

import com.br.mgf.desafioblog.dto.CommentDto;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Authentication authentication);
    void deleteComment(UUID id, Authentication authentication);

}
