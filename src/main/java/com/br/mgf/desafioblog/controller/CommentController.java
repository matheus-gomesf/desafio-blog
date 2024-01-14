package com.br.mgf.desafioblog.controller;

import com.br.mgf.desafioblog.dto.CommentDto;
import com.br.mgf.desafioblog.security.IAuthenticationFacade;
import com.br.mgf.desafioblog.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CommentController {

    private final CommentService commentService;
    private final IAuthenticationFacade iAuthenticationFacade;

    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto) {
        CommentDto comment = commentService.createComment(commentDto, iAuthenticationFacade.getAuthentication());
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        commentService.deleteComment(id, iAuthenticationFacade.getAuthentication());
        return ResponseEntity.ok().build();
    }
}
