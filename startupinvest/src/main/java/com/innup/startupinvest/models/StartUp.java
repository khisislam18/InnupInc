package com.innup.startupinvest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StartUp {
    private Long ID;
    private String title;
    private String description;
    private int price;
    private String author;
    private boolean certified;
    private LocalDate creationDate;
}
