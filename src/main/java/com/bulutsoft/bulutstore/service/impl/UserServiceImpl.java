package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.request.UserCreateRequest;
import com.bulutsoft.bulutstore.request.UserUpdateRequest;
import com.bulutsoft.bulutstore.response.UserResponse;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.mapper.UserMapper;
import com.bulutsoft.bulutstore.repos.UserRepository;
import com.bulutsoft.bulutstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Kullanıcı işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 * DTO <-> Entity dönüşümleri UserMapper ile yapılır.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toResponse);
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        User user = userMapper.toEntity(request);
        // Şifre hashleme işlemi burada yapılmalı (ör: passwordEncoder.encode(request.getPassword()))
        // user.setPassword(...);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateEntityFromRequest(request, user);
        // Şifre güncellemesi gerekiyorsa burada yapılmalı
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserResponse> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toResponse);
    }

    @Override
    public Optional<UserResponse> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toResponse);
    }
}
