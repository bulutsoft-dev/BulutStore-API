package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.entity.User;
import java.util.List;
import java.util.Optional;

/**
 * Kullanıcı işlemleri için servis arayüzü.
 * Temel CRUD ve kullanıcıya özel iş mantığı metotlarını tanımlar.
 */
public interface UserService {
    /**
     * Tüm kullanıcıları döndürür.
     */
    List<User> getAllUsers();

    /**
     * ID ile kullanıcıyı bulur.
     */
    Optional<User> getUserById(Long id);

    /**
     * Yeni kullanıcı kaydeder.
     */
    User createUser(User user);

    /**
     * Kullanıcıyı günceller.
     */
    User updateUser(Long id, User user);

    /**
     * Kullanıcıyı siler.
     */
    void deleteUser(Long id);

    /**
     * Kullanıcı adı ile arama yapar.
     */
    Optional<User> getUserByUsername(String username);

    /**
     * E-posta ile arama yapar.
     */
    Optional<User> getUserByEmail(String email);
}

