package com.sklad.java_api.controller;

import com.sklad.java_api.model.GoodRest;
import com.sklad.java_api.repository.GoodRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class GoodRestController {

    @Autowired
    private GoodRestRepository repository;

    // 1. Стандартный GET (все остатки)
    @GetMapping("/goodrests")
    public List<GoodRest> getAll() {
        return repository.findAll();
    }

    // 2. Метод GetReport из Vue: /goodrests/{stock}/{good}
    @GetMapping("/goodrests/{stock}/{good}")
    public List<GoodRest> getReport(@PathVariable String stock, @PathVariable String good) {
        List<GoodRest> allRests = repository.findAll();
        
        return allRests.stream()
            .filter(r -> stock.equals("Все") || r.getNameStock().equals(stock))
            .filter(r -> good.equals("Все") || r.getNameGood().equals(good))
            .collect(Collectors.toList());
    }

    // 3. Заглушка для AI-советника: /ai-report
    @GetMapping("/ai-report")
    public Map<String, String> getAiReport() {
        // Имитируем логику, которую ждет ваш Vue (upgrade_required)
        return Map.of(
            "status", "upgrade_required",
            "message", "Для анализа остатков через Gemini 2.0 Flash требуется API-ключ. Бэкенд на Java 25 готов к интеграции."
        );
    }

    @PostMapping("/goodrests")
    public GoodRest create(@RequestBody GoodRest rest) {
        return repository.save(rest);
    }

    @DeleteMapping("/goodrests/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
