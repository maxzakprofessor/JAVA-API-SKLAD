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

@RestController
@RequestMapping("/api/auth") // Базовый путь для всех запросов авторизации
@CrossOrigin(origins = "*")  // Разрешаем Vue подключаться
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder; // Сервис для работы с хэшами паролей
    @Autowired private JwtCore jwtCore;

    // Метод для входа (Login)
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // Ищем пользователя в БД по логину
        Optional<User> user = userRepository.findByUsername(username);

        // Сравниваем присланный пароль с хэшем в базе (BCrypt)
        // ВАЖНО: Мы никогда не храним и не сравниваем пароли в открытом виде!
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            // Если всё верно — генерируем токен
            String jwt = jwtCore.generateToken(username);
            // Возвращаем токен клиенту (Vue)
            return ResponseEntity.ok(Map.of("token", jwt, "username", username));
        }

        // Если логин или пароль не подошли
        return ResponseEntity.status(401).body("Ошибка: Неверный логин или пароль");
    }

    // Метод для первой регистрации (SignUp)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        // Перед сохранением обязательно хэшируем пароль!
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Пользователь зарегистрирован");
    }
}
