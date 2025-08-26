package com.bulutsoft.bulutstore.entity;

/**
 * Kullanıcı rolü enumu.
 * Sistemdeki kullanıcı tiplerini belirtir: USER (son kullanıcı), DEVELOPER (uygulama geliştirici), ADMIN (yönetici).
 */
public enum Role {
    /** Son kullanıcı */
    USER,
    /** Uygulama geliştirici */
    DEVELOPER,
    /** Yönetici */
    ADMIN
}
