package com.br.mgf.desafioblog.controller;

import com.br.mgf.desafioblog.dto.PostDto;
import com.br.mgf.desafioblog.security.IAuthenticationFacade;
import com.br.mgf.desafioblog.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PostController {

    private final PostService postService;
    private final IAuthenticationFacade iAuthenticationFacade;

    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostDto postDto) {
        PostDto post = postService.createPost(postDto, iAuthenticationFacade.getAuthentication());
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> list(@PageableDefault Pageable pageable) {
        Page<PostDto> listPosts = postService.listPosts(pageable);
        return ResponseEntity.ok(listPosts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        postService.deletePost(id, iAuthenticationFacade.getAuthentication());
        return ResponseEntity.ok().build();
    }
}
