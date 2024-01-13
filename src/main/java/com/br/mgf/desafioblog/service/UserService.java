package com.br.mgf.desafioblog.service;

import com.br.mgf.desafioblog.dto.UserDto;

public interface UserService {

    UserDto getUserByEmail(String email);
}
