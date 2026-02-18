package com.sklad.java_api;

import com.sklad.java_api.model.User;
import com.sklad.java_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JavaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaApiApplication.class, args);
    }

    // Этот метод запускается автоматически СРАЗУ после старта приложения
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Проверяем, есть ли в таблице users хоть один admin
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                // Хэшируем пароль "123" перед сохранением
                admin.setPassword(passwordEncoder.encode("123")); 
                admin.setRole("ROLE_ADMIN");
                admin.setFullName("Главный Администратор");
                admin.setNeedsPasswordChange(false); // Админу менять не обязательно
                
                userRepository.save(admin);
                
                System.out.println("--------------------------------------");
                System.out.println("ВНИМАНИЕ: Создан дефолтный админ: admin / 123");
                System.out.println("--------------------------------------");
            }
        };
    }
}
