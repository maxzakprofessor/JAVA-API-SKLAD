package com.sklad.java_api.controller;

import com.sklad.java_api.model.Good;
import com.sklad.java_api.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
// Сверяем с ENDPOINTS.GOODS: должен быть /goods
@RequestMapping("/goods") 
@CrossOrigin(origins = "*") // Чтобы Vue мог достучаться до API
public class GoodController {

    @Autowired
    private GoodRepository goodRepository;

    // axios.get(ENDPOINTS.GOODS)
    @GetMapping
    public List<Good> getAll() {
        return goodRepository.findAll();
    }

    // axios.post(ENDPOINTS.GOODS, { nameGood: ... })
    @PostMapping
    public Good create(@RequestBody Good good) {
        return goodRepository.save(good);
    }

    // axios.put(ENDPOINTS.GOODS, { id: ..., nameGood: ... })
    @PutMapping
    public Good update(@RequestBody Good good) {
        return goodRepository.save(good);
    }

    // axios.delete(ENDPOINTS.GOODS + "/" + id)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        goodRepository.deleteById(id);
    }
}
