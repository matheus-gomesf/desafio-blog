package com.br.mgf.desafioblog.service.impl;

import com.br.mgf.desafioblog.dto.FileDto;
import com.br.mgf.desafioblog.dto.PostDto;
import com.br.mgf.desafioblog.dto.UserDto;
import com.br.mgf.desafioblog.entity.FileEntity;
import com.br.mgf.desafioblog.entity.PostEntity;
import com.br.mgf.desafioblog.exception.InvalidDeletionException;
import com.br.mgf.desafioblog.exception.ResourceNotFoundException;
import com.br.mgf.desafioblog.mapper.PostMapper;
import com.br.mgf.desafioblog.repository.PostRepository;
import com.br.mgf.desafioblog.service.FileService;
import com.br.mgf.desafioblog.service.PostService;
import com.br.mgf.desafioblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.br.mgf.desafioblog.mapper.FileMapper.FILE_MAPPER;
import static com.br.mgf.desafioblog.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final FileService fileService;
    private final PostMapper postMapper;

    @Override
    public PostDto createPost(String text, String link, List<MultipartFile> files, Authentication authentication) {

        PostEntity toSave = new PostEntity();
        toSave.setText(text);
        toSave.setLink(link);

        UserDto userDto = userService.getUserByEmail(authentication.getName());
        toSave.setAuthor(USER_MAPPER.dtoToEntity(userDto));

        List<FileDto> fileDtos = fileService.saveFileWithPost(files, toSave);
		List<FileEntity> images = new ArrayList<>(fileDtos.stream().map(FILE_MAPPER::dtoToEntity).toList());
        toSave.setImages(images);

        PostEntity saved = postRepository.save(toSave);

        return postMapper.entityToDto(saved);
    }

    @Override
    public Page<PostDto> listPosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(postMapper::entityToDto);
    }

    @Override
    public void deletePost(UUID id, Authentication authentication) {

        UserDto userDto = userService.getUserByEmail(authentication.getName());
        PostEntity post = findPostById(id);

        if (!post.getAuthor().getId().equals(userDto.getId())) {
            throw new InvalidDeletionException("post");
        }

        postRepository.deleteById(id);
    }

    @Override
    public PostDto getPostById(UUID id) {

        PostEntity post = findPostById(id);
        return postMapper.entityToDto(post);
    }

    private PostEntity findPostById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
    }
}
