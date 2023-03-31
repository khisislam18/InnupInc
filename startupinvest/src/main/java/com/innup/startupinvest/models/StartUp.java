package com.innup.startupinvest.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StartUp {
    private final Long ID;
    private String title;
    private String description;
    private int price;
    private String city;
    private String author;
}
