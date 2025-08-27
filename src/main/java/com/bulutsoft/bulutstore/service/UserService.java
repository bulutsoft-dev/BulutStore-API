package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.request.UserCreateRequest;
import com.bulutsoft.bulutstore.request.UserUpdateRequest;
import com.bulutsoft.bulutstore.response.UserResponse;
import java.util.List;
import java.util.Optional;

/**
 * Kullanıcı işlemleri için servis arayüzü.
 * Temel CRUD ve kullanıcıya özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler UserDto ile yapılır.
 */
public interface UserService {
    /**
     * Tüm kullanıcıları döndürür.
     */
    List<UserResponse> getAllUsers();

    /**
     * ID ile kullanıcıyı bulur.
     */
    Optional<UserResponse> getUserById(Long id);

    /**
     * Yeni kullanıcı kaydeder.
     */
    UserResponse createUser(UserCreateRequest request);

    /**
     * Kullanıcıyı günceller.
     */
    UserResponse updateUser(Long id, UserUpdateRequest request);

    /**
     * Kullanıcıyı siler.
     */
    void deleteUser(Long id);

    /** Kullanıcı developer olmak için başvuru yapar */
    void applyForDeveloper(String applicationText);

    /** Bekleyen developer başvurularını döndürür */
    List<UserResponse> getPendingDeveloperApplications();

    /** Admin: Developer başvurusunu onaylar */
    void approveDeveloper(Long userId);

    /** Admin: Developer başvurusunu reddeder */
    void rejectDeveloper(Long userId);

    /**
     * Kullanıcıyı username ile bulur.
     */
    Optional<UserResponse> getUserByUsername(String username);

    /**
     * Giriş yapan kullanıcının developer başvuru durumunu ve tarihini döndürür.
     */
    com.bulutsoft.bulutstore.response.DeveloperApplicationResponse getOwnDeveloperApplication(String username);
}
