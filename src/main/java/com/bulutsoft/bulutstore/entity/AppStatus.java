package com.bulutsoft.bulutstore.entity;

/**
 * Uygulama onay durumu enumu.
 * Uygulamanın mağazada yayınlanma sürecindeki durumunu belirtir.
 * PENDING: Onay bekliyor
 * APPROVED: Onaylandı ve yayında
 * REJECTED: Reddedildi
 */
public enum AppStatus {
    /** Onay bekliyor */
    PENDING,
    /** Onaylandı ve yayında */
    APPROVED,
    /** Reddedildi */
    REJECTED
}
