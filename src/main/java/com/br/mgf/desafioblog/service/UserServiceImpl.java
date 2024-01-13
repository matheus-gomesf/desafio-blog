package com.br.mgf.desafioblog.service;

import com.br.mgf.desafioblog.dto.UserDto;
import com.br.mgf.desafioblog.entity.UserEntity;
import com.br.mgf.desafioblog.exception.ResourceNotFoundException;
import com.br.mgf.desafioblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.br.mgf.desafioblog.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUserByEmail(String email) {
        UserDetails user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return USER_MAPPER.entityToDto((UserEntity) user);
    }
}
