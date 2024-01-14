package com.br.mgf.desafioblog.service;

import com.br.mgf.desafioblog.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface PostService {

    PostDto createPost(PostDto postDto, Authentication authentication);

    Page<PostDto> listPosts(Pageable pageable);

    void deletePost(UUID id, Authentication authentication);

    PostDto getPostById(UUID id);
}
