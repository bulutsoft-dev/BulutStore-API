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
        if (user.getStatus() == null) {
            user.setStatus(com.bulutsoft.bulutstore.entity.UserStatus.ACTIVE);
        }
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
    @Transactional
    public void applyForDeveloper() {
        // Giriş yapan kullanıcıyı al
        String username = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        if (user.getDeveloperApplicationDate() != null) {
            throw new RuntimeException("Bir kullanıcı yalnızca bir kez geliştirici başvurusu yapabilir.");
        }
        if (user.getDeveloperApplicationStatus() == com.bulutsoft.bulutstore.entity.DeveloperApplicationStatus.PENDING) {
            throw new RuntimeException("Zaten bekleyen bir başvurunuz var.");
        }
        user.setDeveloperApplicationStatus(com.bulutsoft.bulutstore.entity.DeveloperApplicationStatus.PENDING);
        user.setDeveloperApplicationDate(java.time.LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getPendingDeveloperApplications() {
        List<User> pending = userRepository.findByDeveloperApplicationStatus(com.bulutsoft.bulutstore.entity.DeveloperApplicationStatus.PENDING);
        return userMapper.toResponseList(pending);
    }

    @Override
    @Transactional
    public void approveDeveloper(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        user.setDeveloperApplicationStatus(com.bulutsoft.bulutstore.entity.DeveloperApplicationStatus.APPROVED);
        user.setRole(com.bulutsoft.bulutstore.entity.Role.DEVELOPER);
        user.setStatus(com.bulutsoft.bulutstore.entity.UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void rejectDeveloper(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        user.setDeveloperApplicationStatus(com.bulutsoft.bulutstore.entity.DeveloperApplicationStatus.REJECTED);
        userRepository.save(user);
    }

    @Override
    public Optional<UserResponse> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toResponse);
    }

    @Override
    public com.bulutsoft.bulutstore.response.DeveloperApplicationResponse getOwnDeveloperApplication(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        return new com.bulutsoft.bulutstore.response.DeveloperApplicationResponse(
                user.getDeveloperApplicationStatus(),
                user.getDeveloperApplicationDate()
        );
    }
}
