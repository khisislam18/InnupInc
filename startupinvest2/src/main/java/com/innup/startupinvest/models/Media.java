package com.innup.startupinvest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.sql.Blob;
import java.util.UUID;

@Entity
@Table(name = "medias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "mediaPath")
    private String mediaPath;
    @Column(name = "size")
    private Long size;
    @Column(name = "type")
    private String contentType;
    @Column(name = "isPreview")
    private boolean isPreview;
    @Lob
    private byte[] bytes;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private StartUp startUp;
}