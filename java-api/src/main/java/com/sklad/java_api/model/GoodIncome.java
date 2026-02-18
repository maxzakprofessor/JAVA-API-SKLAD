package com.sklad.java_api.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "goodincomes") // Имя таблицы как в Django
public class GoodIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
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

    // Магия для связи с Vue: JsonProperty стыкует имя, JsonFormat — формат даты с буквой 'Z'
    @JsonProperty("datetime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime datetime;
}
