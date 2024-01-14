package com.br.mgf.desafioblog.controller;

import com.br.mgf.desafioblog.dto.AuthenticationDto;
import com.br.mgf.desafioblog.dto.RegisterDto;
import com.br.mgf.desafioblog.service.impl.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
   

    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authenticationDto){
        return authorizationService.login(authenticationDto);
    }


    @PostMapping("/register")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Object> register (@RequestBody @Valid RegisterDto registerDto){
        return authorizationService.register(registerDto);
    }
}