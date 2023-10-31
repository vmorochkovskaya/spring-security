package jmp.cloud.service.api.main.service;


import jmp.dto.main.dto.UserRequestDto;
import jmp.dto.main.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDto createUser(UserRequestDto user);

    UserResponseDto updateUser(UserRequestDto user);

    Optional<UserResponseDto> getUser(Long id);

    void deleteUser(Long id);

    List<UserResponseDto> getAllUsers();
}
