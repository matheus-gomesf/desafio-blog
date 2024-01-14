package com.br.mgf.desafioblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CommentDto {
    private UUID id;
    private PostDto postDto;
    private String text;
    private Date createdAt;
    private UserDto authorDto;
}
