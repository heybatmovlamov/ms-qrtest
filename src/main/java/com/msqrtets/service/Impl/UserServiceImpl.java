package com.msqrtets.service.Impl;

import com.msqrtets.entity.User;
import com.msqrtets.mapper.UserMapper;
import com.msqrtets.model.dto.UserRequest;
import com.msqrtets.model.dto.UserResponse;
import com.msqrtets.repository.UserRepository;
import com.msqrtets.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;


    @Transactional
    @Override
    public UserResponse createUser(UserRequest dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public UserResponse getUserById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("No user with id : " + id));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + id));
        mapper.updateUserFromDto(dto, user);
        return mapper.toDto(repository.save(user));

    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);

    }
}
