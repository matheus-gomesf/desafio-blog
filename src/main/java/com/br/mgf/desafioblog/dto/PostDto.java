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
public class PostDto {
    private UUID id;
    private Date createdAt;
    private Date updatedAt;
    private String text;
    private String images;
    private String link;
    private UserDto authorDto;
}
