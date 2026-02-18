package com.sklad.java_api.repository;

import com.sklad.java_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Репозиторий для работы с пользователями.
 * Наследуем JpaRepository, чтобы получить методы findById, save и т.д.
 * Применяем принцип D (Dependency Inversion) из SOLID.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Специальный метод для поиска пользователя по логину.
    // Spring Data JPA сам создаст SQL запрос: SELECT * FROM users WHERE username = ?
    Optional<User> findByUsername(String username);
}
