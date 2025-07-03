package com.msqrtets.mapper;

import com.msqrtets.entity.User;
import com.msqrtets.model.dto.UserRequest;
import com.msqrtets.model.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest dto);

    UserResponse toDto(User user);

    List<UserResponse> toDtoList(List<User> users);

    List<User> toEntityList(List<UserRequest> users);

    void updateUserFromDto(UserRequest dto,@MappingTarget User user);
}

