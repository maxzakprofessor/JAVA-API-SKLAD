package com.sklad.java_api.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "goodincomes")
public class GoodIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("idStock")
    private Integer idStock = 0;

    @Column(length = 500)
    @JsonProperty("nameStock")
    private String nameStock;

    @JsonProperty("idGood")
    private Integer idGood = 0;

    @Column(length = 500)
    @JsonProperty("nameGood")
    private String nameGood;

    @JsonProperty("qty")
    private Integer qty = 0;

    @JsonProperty("datetime")
    private LocalDateTime datetime;
}
