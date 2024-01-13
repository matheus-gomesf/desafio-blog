package com.br.mgf.desafioblog.service.impl;

import com.br.mgf.desafioblog.dto.AuthenticationDto;
import com.br.mgf.desafioblog.dto.LoginResponseDto;
import com.br.mgf.desafioblog.dto.RegisterDto;
import com.br.mgf.desafioblog.entity.UserEntity;
import com.br.mgf.desafioblog.exception.ResourceNotFoundException;
import com.br.mgf.desafioblog.repository.UserRepository;
import com.br.mgf.desafioblog.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService{

    private final ApplicationContext context;
    
    private final UserRepository userRepository;

    private final TokenService tokenService;

    private AuthenticationManager authenticationManager;
    
    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    } 

    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto data) {
        authenticationManager = context.getBean(AuthenticationManager.class);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }


    public ResponseEntity<Object> register (@RequestBody RegisterDto registerDto) {
        if (this.userRepository.findByEmail(registerDto.email()).isPresent()) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        
        UserEntity newUser = new UserEntity(registerDto.email(), encryptedPassword, registerDto.role());
        newUser.setCreatedAt(new Date(System.currentTimeMillis()));
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }



    
}
