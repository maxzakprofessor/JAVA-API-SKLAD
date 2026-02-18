package com.sklad.java_api.controller;

import com.sklad.java_api.model.GoodMove;
import com.sklad.java_api.repository.GoodMoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/goodmoves") // Сверяем с вашим ENDPOINTS.GOODMOVES
@CrossOrigin(origins = "*")    // Чтобы Vue.js мог отправлять запросы
public class GoodMoveController {

    @Autowired
    private GoodMoveRepository repository;

    // GET: axios.get(ENDPOINTS.GOODMOVES)
    @GetMapping
    public List<GoodMove> getAll() {
        return repository.findAll();
    }

    // POST: axios.post(ENDPOINTS.GOODMOVES, { ... })
    @PostMapping
    public GoodMove create(@RequestBody GoodMove move) {
        move.setId(null); // Убеждаемся, что создается новая запись
        return repository.save(move);
    }

    // PUT: axios.put(ENDPOINTS.GOODMOVES, { id: this.id, ... })
    @PutMapping
    public GoodMove update(@RequestBody GoodMove move) {
        // Spring обновит запись, так как в объекте move придет существующий id
        return repository.save(move);
    }

    // DELETE: axios.delete(ENDPOINTS.GOODMOVES + "/" + id)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
