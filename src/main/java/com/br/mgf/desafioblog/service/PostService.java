package com.br.mgf.desafioblog.service;

import com.br.mgf.desafioblog.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Page<PostDto> listPosts(Pageable pageable);

    void deletePost(UUID id, Authentication authentication);

    PostDto getPostById(UUID id);

	PostDto createPost(String text, String link, List<MultipartFile> files, Authentication authentication);
}
