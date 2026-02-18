package com.sklad.java_api.controller;

import com.sklad.java_api.model.GoodRest;
import com.sklad.java_api.service.GoodRestService; // Импортируем наш сервис с логикой
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodrests")
@CrossOrigin(origins = "*")
public class GoodRestController {

    @Autowired
    private GoodRestService restService; // Внедряем сервис расчета

    /**
     * Вызывается из Vue: axios.get(ENDPOINTS.GOODRESTS + "/" + this.nameStock + "/" + this.nameGood)
     */
    @GetMapping("/{nameStock}/{nameGood}")
    public List<GoodRest> getReport(
            @PathVariable String nameStock, 
            @PathVariable String nameGood) {
        
        // 1. Сначала вызываем расчет (аналог вашего кода на C#)
        // Метод внутри себя сделает циклы, deleteAll() и saveAll()
        return restService.calculateAndSaveRests(nameStock, nameGood);
        
        // 2. Результат возвращается во Vue как response.data
    }
}
