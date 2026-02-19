package com.sklad.java_api.service;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.FontFactory;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.sklad.java_api.model.*;
import com.sklad.java_api.repository.*;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodRestService {

    @Autowired private GoodRepository goodRepo;
    @Autowired private StockRepository stockRepo;
    @Autowired private GoodIncomeRepository incomeRepo;
    @Autowired private GoodMoveRepository moveRepo;
    @Autowired private GoodRestRepository restRepo;

    @Transactional
    public List<GoodRest> calculateAndSaveRests(String wnameStock, String wnameGood) {
        // 1. Загружаем справочники
        List<Good> allGoodsInDb = goodRepo.findAll();
        List<Stock> allStocksInDb = stockRepo.findAll();
        
        // 2. Загружаем все движения (приходы и перемещения)
        List<GoodIncome> allIncomes = incomeRepo.findAll();
        List<GoodMove> allMoves = moveRepo.findAll();
        
        List<GoodRest> results = new ArrayList<>();

        // 3. ЛОГИКА ФИЛЬТРАЦИИ (Аналог ваших if в C#)
        // Если выбрано "Все" — берем весь список имен из БД, иначе — список из одного имени
        List<String> goodsToProcess = wnameGood.equals("Все") 
            ? allGoodsInDb.stream().map(Good::getNameGood).collect(Collectors.toList()) 
            : List.of(wnameGood);

        List<String> stocksToProcess = wnameStock.equals("Все") 
            ? allStocksInDb.stream().map(Stock::getNameStock).collect(Collectors.toList()) 
            : List.of(wnameStock);

        // 4. УНИВЕРСАЛЬНЫЙ ЦИКЛ РАСЧЕТА
        for (String gName : goodsToProcess) {
            for (String sName : stocksToProcess) {
                
                // Считаем приходы для конкретной пары Товар-Склад
                int qtyIncomes = allIncomes.stream()
                    .filter(i -> gName.equals(i.getNameGood()) && sName.equals(i.getNameStock()))
                    .mapToInt(GoodIncome::getQty).sum();

                // Считаем уходы (откуда ушло)
                int qtyMovesFrom = allMoves.stream()
                    .filter(m -> gName.equals(m.getNameGood()) && sName.equals(m.getNameStockFrom()))
                    .mapToInt(GoodMove::getQty).sum();

                // Считаем приходы от перемещений (куда пришло)
                int qtyMovesTo = allMoves.stream()
                    .filter(m -> gName.equals(m.getNameGood()) && sName.equals(m.getNameStockTowhere()))
                    .mapToInt(GoodMove::getQty).sum();

                int finalQty = qtyIncomes - qtyMovesFrom + qtyMovesTo;

                // Добавляем в отчет только если остаток > 0 (как в C#)
                if (finalQty > 0) {
                    GoodRest rest = new GoodRest();
                    rest.setNameGood(gName);
                    rest.setNameStock(sName);
                    rest.setQty(finalQty);
                    results.add(rest);
                }
            }
        }

        // 5. ОБНОВЛЕНИЕ БАЗЫ (Аналог RemoveRange + AddRange + SaveChanges)
        restRepo.deleteAll(); 
        return restRepo.saveAll(results);
    }

    public void exportToPdf(String stock, String good, HttpServletResponse response) throws IOException {
    // 1. Получаем данные для отчета (наш готовый расчет)
    List<GoodRest> data = calculateAndSaveRests(stock, good);

    // 2. Настраиваем документ A4
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, response.getOutputStream());

    document.open();

    // --- ЛОГИКА ДЛЯ РУССКОГО ЯЗЫКА ---
    // Указываем путь к системному шрифту Windows для поддержки кириллицы
    String fontPath = "C:/Windows/Fonts/arial.ttf"; 
    BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font fontTitle = new Font(bf, 18, Font.BOLD);
    Font fontTable = new Font(bf, 12, Font.NORMAL);
    // ---------------------------------

    // Заголовок
    Paragraph p = new Paragraph("Отчет по остаткам ТМЦ", fontTitle);
    p.setAlignment(Paragraph.ALIGN_CENTER);
    document.add(p);

    // Таблица (3 колонки)
    PdfPTable table = new PdfPTable(3);
    table.setWidthPercentage(100f);
    table.setSpacingBefore(15);

    // Шапка таблицы
    table.addCell(new Phrase("Склад", fontTable));
    table.addCell(new Phrase("Товар", fontTable));
    table.addCell(new Phrase("Кол-во", fontTable));

    // Данные
    for (GoodRest rest : data) {
        table.addCell(new Phrase(rest.getNameStock(), fontTable));
        table.addCell(new Phrase(rest.getNameGood(), fontTable));
        table.addCell(new Phrase(String.valueOf(rest.getQty()), fontTable));
    }

    document.add(table);
    document.close();
}

}
