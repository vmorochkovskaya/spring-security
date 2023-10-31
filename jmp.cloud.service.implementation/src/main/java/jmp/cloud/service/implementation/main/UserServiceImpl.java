package jmp.cloud.service.implementation.main;

import jmp.cloud.service.api.main.repository.UserRepository;
import jmp.cloud.service.api.main.service.UserService;
import jmp.dto.main.User;
import jmp.dto.main.dto.UserRequestDto;
import jmp.dto.main.dto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        return createUser(userRequestDto);
    }

    @Override
    public Optional<UserResponseDto> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(us -> modelMapper.map(us, UserResponseDto.class));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }
}
