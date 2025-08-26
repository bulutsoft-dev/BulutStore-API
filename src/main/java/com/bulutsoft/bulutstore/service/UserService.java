package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.dto.UserDto;
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
    List<UserDto> getAllUsers();

    /**
     * ID ile kullanıcıyı bulur.
     */
    Optional<UserDto> getUserById(Long id);

    /**
     * Yeni kullanıcı kaydeder.
     */
    UserDto createUser(UserDto userDto);

    /**
     * Kullanıcıyı günceller.
     */
    UserDto updateUser(Long id, UserDto userDto);

    /**
     * Kullanıcıyı siler.
     */
    void deleteUser(Long id);

    /**
     * Kullanıcı adı ile arama yapar.
     */
    Optional<UserDto> getUserByUsername(String username);

    /**
     * E-posta ile arama yapar.
     */
    Optional<UserDto> getUserByEmail(String email);
}
