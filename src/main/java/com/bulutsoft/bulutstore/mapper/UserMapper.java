package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.request.UserCreateRequest;
import com.bulutsoft.bulutstore.request.UserUpdateRequest;
import com.bulutsoft.bulutstore.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * User <-> UserDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    // displayName ve website alanlarını User <-> UserResponse arasında map et
    @org.mapstruct.Mapping(source = "displayName", target = "displayName")
    @org.mapstruct.Mapping(source = "website", target = "website")
    @org.mapstruct.Mapping(source = "developerApplicationText", target = "developerApplicationText")
    UserResponse toResponse(User user);
    List<UserResponse> toResponseList(List<User> users);
    User toEntity(UserCreateRequest request);
    void updateEntityFromRequest(UserUpdateRequest request, @org.mapstruct.MappingTarget User user);
}
