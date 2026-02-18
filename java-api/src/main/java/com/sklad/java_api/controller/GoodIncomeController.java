package com.sklad.java_api.controller;

import com.sklad.java_api.model.GoodIncome;
import com.sklad.java_api.repository.GoodIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/goodincomes") // Соответствует вашему ENDPOINTS.GOODINCOMES
@CrossOrigin(origins = "*")    // Разрешает запросы от вашего Vue (CORS)
public class GoodIncomeController {

    @Autowired
    private GoodIncomeRepository repository;

    // GET: axios.get(ENDPOINTS.GOODINCOMES)
    // Возвращает список всех поступлений для таблицы во Vue
    @GetMapping
    public List<GoodIncome> getAll() {
        return repository.findAll();
    }

    // POST: axios.post(ENDPOINTS.GOODINCOMES, { ... })
    // Принимает объект с полями idStock, nameStock, idGood, nameGood, qty, datetime
    @PostMapping
    public GoodIncome create(@RequestBody GoodIncome income) {
        // Гарантируем, что ID пустой для создания новой записи
        income.setId(null); 
        return repository.save(income);
    }

    // PUT: axios.put(ENDPOINTS.GOODINCOMES, { ... })
    // Используется в методе updateClick() во Vue
    @PutMapping
    public GoodIncome update(@RequestBody GoodIncome income) {
        // JpaRepository выполнит SQL UPDATE, так как в объекте есть ID
        return repository.save(income);
    }

    // DELETE: axios.delete(ENDPOINTS.GOODINCOMES + "/" + id)
    // Удаление записи по ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
