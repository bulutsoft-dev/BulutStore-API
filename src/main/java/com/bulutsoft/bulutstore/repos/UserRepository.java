package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * User entity'si için CRUD ve özel sorguların tanımlandığı repository arayüzü.
 * JpaRepository, temel CRUD işlemlerini otomatik olarak sağlar.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Kullanıcı adından kullanıcıyı bulur.
     * @param username kullanıcı adı
     * @return kullanıcı (varsa)
     */
    Optional<User> findByUsername(String username);

    /**
     * E-posta adresinden kullanıcıyı bulur.
     * @param email e-posta
     * @return kullanıcı (varsa)
     */
    Optional<User> findByEmail(String email);

    /**
     * Kullanıcı adı veya e-posta ile kullanıcıyı bulur.
     * @param username kullanıcı adı
     * @param email e-posta
     * @return kullanıcı (varsa)
     */
    Optional<User> findByUsernameOrEmail(String username, String email);
}

