package com.sklad.java_api.controller;

import com.sklad.java_api.model.Stock;
import com.sklad.java_api.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Сверяем с ENDPOINTS.STOCKS: адрес http://localhost:8000/stocks
@RequestMapping("/stocks")
@CrossOrigin(origins = "*") // Разрешаем запросы от Vue
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    // GET: axios.get(ENDPOINTS.STOCKS)
    @GetMapping
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    // POST: axios.post(ENDPOINTS.STOCKS, { nameStock: this.nameStock })
    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        // Устанавливаем id в null, чтобы база сгенерировала новый (как AutoField в Django)
        stock.setId(null); 
        return stockRepository.save(stock);
    }

    // PUT: axios.put(ENDPOINTS.STOCKS, { id: this.id, nameStock: this.nameStock })
    @PutMapping
    public Stock update(@RequestBody Stock stock) {
        // JpaRepository выполнит UPDATE, так как в объекте пришел id
        return stockRepository.save(stock);
    }

    // DELETE: axios.delete(ENDPOINTS.STOCKS + "/" + id)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stockRepository.deleteById(id);
    }
}
