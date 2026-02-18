package com.sklad.java_api.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "goodmoves")
public class GoodMove {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    @JsonProperty("nameStockFrom")
    private String nameStockFrom;

    @Column(length = 500)
    @JsonProperty("nameStockTowhere")
    private String nameStockTowhere;

    @Column(length = 500)
    @JsonProperty("nameGood")
    private String nameGood;

    @JsonProperty("qty")
    private Integer qty = 0;

    @JsonProperty("datetime")
    private LocalDateTime datetime;
}
