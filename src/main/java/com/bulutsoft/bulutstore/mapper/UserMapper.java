package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.request.UserCreateRequest;
import com.bulutsoft.bulutstore.request.UserUpdateRequest;
import com.bulutsoft.bulutstore.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * User <-> UserDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponse toResponse(User user);
    List<UserResponse> toResponseList(List<User> users);
    User toEntity(UserCreateRequest request);
    void updateEntityFromRequest(UserUpdateRequest request, @org.mapstruct.MappingTarget User user);
}
