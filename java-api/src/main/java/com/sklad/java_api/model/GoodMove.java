package com.sklad.java_api.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "goodmoves") // Таблица во множественном числе как в Django
public class GoodMove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(length = 500)
    @JsonProperty("nameStockFrom") // Стык с Vue v-model="nameStockFrom"
    private String nameStockFrom;

    @Column(length = 500)
    @JsonProperty("nameStockTowhere") // Стык с Vue v-model="nameStockTowhere"
    private String nameStockTowhere;

    @Column(length = 500)
    @JsonProperty("nameGood")
    private String nameGood;

    @JsonProperty("qty")
    private Integer qty = 0;

    // Обработка даты ISO (2024-05-20T12:00:00Z) из Vue
    @JsonProperty("datetime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime datetime;
}
