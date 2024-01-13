package com.br.mgf.desafioblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDto(@NotNull @Email String email, @NotNull String password) {
    
}