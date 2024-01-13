package com.br.mgf.desafioblog.dto;

import com.br.mgf.desafioblog.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(@NotNull @Email String email, @NotNull String password, @NotNull UserRole role ) {
    
}