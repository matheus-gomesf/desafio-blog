package com.br.mgf.desafioblog.service.impl;

import com.br.mgf.desafioblog.dto.CommentDto;
import com.br.mgf.desafioblog.dto.PostDto;
import com.br.mgf.desafioblog.dto.UserDto;
import com.br.mgf.desafioblog.entity.CommentEntity;
import com.br.mgf.desafioblog.exception.InvalidDeletionException;
import com.br.mgf.desafioblog.exception.ResourceNotFoundException;
import com.br.mgf.desafioblog.repository.CommentRepository;
import com.br.mgf.desafioblog.service.CommentService;
import com.br.mgf.desafioblog.service.PostService;
import com.br.mgf.desafioblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.br.mgf.desafioblog.mapper.CommentMapper.COMMENT_MAPPER;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    @Override
    public CommentDto createComment(CommentDto commentDto, Authentication authentication) {

        UserDto author = userService.getUserByEmail(authentication.getName());
        commentDto.setAuthorDto(author);

        PostDto post = postService.getPostById(commentDto.getPostDto().getId());
        commentDto.setPostDto(post);

        CommentEntity toSave = COMMENT_MAPPER.dtoToEntity(commentDto);

        CommentEntity saved = commentRepository.save(toSave);

        return COMMENT_MAPPER.entityToDto(saved);
    }

    @Override
    public void deleteComment(UUID id, Authentication authentication) {
        UserDto userDto = userService.getUserByEmail(authentication.getName());
        CommentEntity comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id.toString()));

        if (!comment.getAuthor().getId().equals(userDto.getId())) {
            throw new InvalidDeletionException("comment");
        }
    }
}
