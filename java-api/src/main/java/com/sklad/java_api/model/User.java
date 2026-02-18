package com.sklad.java_api.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Модель Пользователя (Entity)
 * Отвечает за структуру таблицы 'users' в PostgreSQL.
 * Соблюдаем принцип S (Single Responsibility): этот класс только описывает данные.
 */
@Entity
@Data
@Table(name = "users") // Имя таблицы в БД
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Уникальный логин, не может быть пустым
    @Column(unique = true, nullable = false)
    private String username;

    // Хэшированный пароль (мы никогда не храним пароли в открытом виде!)
    @Column(nullable = false)
    private String password;

    // Роль пользователя (например, USER или ADMIN)
    private String role = "USER";
}
