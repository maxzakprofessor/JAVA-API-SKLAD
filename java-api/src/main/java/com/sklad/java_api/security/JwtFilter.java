package com.sklad.java_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired private JwtCore jwtCore;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        // 1. Ищем заголовок "Authorization: Bearer <token>"
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                // 2. Вытаскиваем имя пользователя из токена
                username = jwtCore.getNameFromToken(jwt);
            } catch (Exception e) {
                // Если токен поддельный или просрочен — игнорируем
            }
        }

        // 3. Если пользователь найден, авторизуем его в системе Spring
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                username, null, Collections.emptyList()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // 4. Идем дальше по цепочке фильтров
        filterChain.doFilter(request, response);
    }
}
