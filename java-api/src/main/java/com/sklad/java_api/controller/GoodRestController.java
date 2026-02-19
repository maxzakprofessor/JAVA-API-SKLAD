package com.sklad.java_api.controller;

import com.sklad.java_api.model.GoodRest;
import com.sklad.java_api.service.GoodRestService; // Импортируем наш сервис с логикой

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.io.IOException;

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

    @GetMapping("/export/pdf/{nameStock}/{nameGood}")
public void exportToPDF(@PathVariable String nameStock, @PathVariable String nameGood, HttpServletResponse response) throws IOException {
    // Устанавливаем тип контента и имя файла
    response.setContentType("application/pdf");
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=report_" + nameStock + ".pdf";
    response.setHeader(headerKey, headerValue);

    // Вызываем генерацию
    restService.exportToPdf(nameStock, nameGood, response);
}

}
