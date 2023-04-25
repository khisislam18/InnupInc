package com.innup.startupinvest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name="certified")
    private boolean certified;
    @Column(name="creationDate")
    private LocalDate creationDate;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private StartUp startUp;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "startUp")
    private List<Media> medias = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    private Long previewMediaId;

    @PrePersist
    private void init(){
        creationDate = LocalDate.now();
    }

    public void addMediaToProduct(Media media) {
        media.setStartUp(this);
        medias.add(media);
    }
}
