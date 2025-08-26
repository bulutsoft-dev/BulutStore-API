package com.bulutsoft.bulutstore.entity;

/**
 * Kullanıcı statüsü enumu.
 * Sistemdeki kullanıcıların onay ve aktiflik durumunu belirtir.
 * ACTIVE: Aktif kullanıcı
 * PENDING: Geliştirici başvurusu bekliyor
 * REJECTED: Başvuru reddedildi
 */
public enum UserStatus {
    /** Aktif kullanıcı */
    ACTIVE,
    /** Geliştirici başvurusu bekliyor */
    PENDING,
    /** Başvuru reddedildi */
    REJECTED
}
