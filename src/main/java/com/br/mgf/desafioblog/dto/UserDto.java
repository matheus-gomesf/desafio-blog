package com.br.mgf.desafioblog.dto;

import com.br.mgf.desafioblog.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class UserDto {

    private UUID id;
    private String email;
    @JsonIgnore
    private String password;
    private UserRole userRole;
    private Date createdAt;
    private Date updatedAt;
}
