package com.sklad.java_api.controller;

import com.sklad.java_api.model.User;
import com.sklad.java_api.repository.UserRepository;
import com.sklad.java_api.security.JwtCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Контроллер авторизации и управления доступом.
 * Реализует стандарты безопасности корпоративного уровня (JWT + BCrypt).
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtCore jwtCore;

    /**
     * ВХОД В СИСТЕМУ (Login)
     * Сверяет хэш пароля и выдает JWT токен.
     */
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            String jwt = jwtCore.generateToken(username);
            return ResponseEntity.ok(Map.of(
                "token", jwt, 
                "username", username,
                "needsChange", user.get().isNeedsPasswordChange() // Сообщаем Vue, нужно ли менять пароль
            ));
        }

        return ResponseEntity.status(401).body("Ошибка: Неверный логин или пароль");
    }

    /**
     * СОЗДАНИЕ ПОЛЬЗОВАТЕЛЯ АДМИНИСТРАТОРОМ (Enterprise Standard)
     * Генерирует случайный пароль для нового сотрудника.
     */
    @PostMapping("/admin/create-user")
    public ResponseEntity<?> adminCreateUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String fullName = request.get("fullName");

        // 1. Проверяем, нет ли уже такого пользователя
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Пользователь с таким логином уже существует");
        }

        // 2. Генерируем случайный временный пароль (8 символов)
        String rawPassword = UUID.randomUUID().toString().substring(0, 8);

        // 3. Создаем объект и хэшируем пароль для БД
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setFullName(fullName);
        newUser.setPassword(passwordEncoder.encode(rawPassword));
        newUser.setRole("ROLE_USER");
        newUser.setNeedsPasswordChange(true); // Флаг обязательной смены при первом входе

        userRepository.save(newUser);

        // 4. Возвращаем ОТКРЫТЫЙ пароль только сейчас, чтобы админ его передал сотруднику
        return ResponseEntity.ok(Map.of(
            "username", username,
            "temporaryPassword", rawPassword,
            "status", "Учетная запись создана. Передайте временный пароль сотруднику."
        ));
    }

    /**
     * СМЕНА ПАРОЛЯ (При первом входе или по желанию)
     */
    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String newPassword = request.get("newPassword");

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setNeedsPasswordChange(false); // Снимаем флаг принудительной смены
            userRepository.save(user);
            return ResponseEntity.ok("Пароль успешно обновлен");
        }
        return ResponseEntity.status(404).body("Пользователь не найден");
    }
}
