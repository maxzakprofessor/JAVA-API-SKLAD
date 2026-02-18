package com.sklad.java_api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.Date;
import javax.crypto.SecretKey;

/**
 * Класс JwtCore отвечает за создание JSON Web Token (JWT).
 * Соблюдаем принцип S (Single Responsibility): только генерация и валидация токена.
 */
@Component
public class JwtCore {

    // Секретный ключ (минимум 32 символа). 
    // В реальном банке или крупной компании его хранят в секретных настройках (Vault/Env).
    private final String secret = "secretKey-must-be-very-long-and-secure-for-sklad-api-2025";
    private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    
    // Время жизни токена — 1 сутки (в миллисекундах)
    private final int lifetime = 86400000; 

    /**
     * Генерирует токен на основе имени пользователя.
     * Этот токен Vue.js будет сохранять в localStorage.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // Помещаем имя пользователя внутрь "посылки"
                .issuedAt(new Date()) // Время создания
                .expiration(new Date((new Date()).getTime() + lifetime)) // Срок годности
                .signWith(key) // Запечатываем нашей секретной подписью
                .compact(); // Собираем в одну строку
    }

    /**
     * Извлекает имя пользователя из токена (нужно для проверки "кто пришел").
     */
    public String getNameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
