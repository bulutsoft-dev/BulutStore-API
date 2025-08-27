package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.DeveloperApplicationStatus;
import com.bulutsoft.bulutstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User entity'si için CRUD ve özel sorguların tanımlandığı repository arayüzü.
 * JpaRepository, temel CRUD işlemlerini otomatik olarak sağlar.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByDeveloperApplicationStatus(DeveloperApplicationStatus status);
}
