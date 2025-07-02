package com.msqrtets.service.Impl;

import com.msqrtets.model.User;
import com.msqrtets.model.dto.UserRequestDto;
import com.msqrtets.model.dto.UserResponseDto;
import com.msqrtets.model.mapper.UserMapper;
import com.msqrtets.repository.UserRepository;
import com.msqrtets.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(requestDto)));
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("No user with id : " + id));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id : " + id));

        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }
}
