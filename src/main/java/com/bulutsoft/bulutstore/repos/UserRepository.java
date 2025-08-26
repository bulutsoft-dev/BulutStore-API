package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User entity'si için CRUD ve özel sorguların tanımlandığı repository arayüzü.
 * JpaRepository, temel CRUD işlemlerini otomatik olarak sağlar.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
