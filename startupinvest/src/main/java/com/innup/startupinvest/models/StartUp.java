package com.innup.startupinvest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name="startup")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartUp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long ID;
    @Column(name="title")
    private String title;
    @Column(name="description",columnDefinition = "text")
    private String description;
    @Column(name="price")
    private int price;
    @Column(name="author")
    private String author;
    @Column(name="certified")
    private boolean certified;
    @Column(name="creationDate")
    private LocalDate creationDate;
}
