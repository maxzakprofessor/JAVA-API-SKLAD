package com.sklad.java_api.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Модель Пользователя (Entity)
 * Соответствует таблице 'users' в PostgreSQL.
 * Реализовано согласно стандартам безопасности корпоративных систем.
 */
@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Уникальный логин (например, i.ivanov)
    @Column(unique = true, nullable = false)
    private String username;

    // Хэшированный пароль (BCrypt). В базе никогда не хранится открытый текст.
    @Column(nullable = false)
    private String password;

    // Роль пользователя: ROLE_USER или ROLE_ADMIN
    private String role = "ROLE_USER";

    /**
     * Флаг обязательной смены пароля. 
     * В госучреждениях администратор выдает временный пароль, 
     * который пользователь обязан сменить при первом входе.
     */
    @Column(name = "needs_password_change")
    private boolean needsPasswordChange = true;

    // Поле для хранения ФИО или полного названия должности (опционально)
    @Column(length = 500)
    private String fullName;
}
