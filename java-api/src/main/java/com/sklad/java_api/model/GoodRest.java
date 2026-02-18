package com.sklad.java_api.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@Table(name = "goodrests")
public class GoodRest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    @JsonProperty("nameStock")
    private String nameStock;

    @Column(length = 500)
    @JsonProperty("nameGood")
    private String nameGood;

    @JsonProperty("qty")
    private Integer qty = 0;
}
