package com.br.mgf.desafioblog.service.impl;

import com.br.mgf.desafioblog.dto.PostDto;
import com.br.mgf.desafioblog.dto.UserDto;
import com.br.mgf.desafioblog.entity.PostEntity;
import com.br.mgf.desafioblog.exception.InvalidDeletionException;
import com.br.mgf.desafioblog.exception.ResourceNotFoundException;
import com.br.mgf.desafioblog.repository.PostRepository;
import com.br.mgf.desafioblog.service.PostService;
import com.br.mgf.desafioblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.br.mgf.desafioblog.mapper.PostMapper.POST_MAPPER;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Override
    public PostDto createPost(PostDto postDto, Authentication authentication) {

        UserDto userDto = userService.getUserByEmail(authentication.getName());
        postDto.setAuthorDto(userDto);

        PostEntity toSave = POST_MAPPER.dtoToEntity(postDto);
        PostEntity saved = postRepository.save(toSave);

        return POST_MAPPER.entityToDto(saved);
    }

    @Override
    public Page<PostDto> listPosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(POST_MAPPER::entityToDto);
    }

    @Override
    public void deletePost(UUID id, Authentication authentication) {

        UserDto userDto = userService.getUserByEmail(authentication.getName());
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

        if (!post.getAuthor().getId().equals(userDto.getId())) {
            throw new InvalidDeletionException("post");
        }

        postRepository.deleteById(id);
    }
}
