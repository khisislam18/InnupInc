package com.innup.startupinvest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "replyComments")
public class ReplyComment{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private StartUpComment startUpComment;

    private String comment;

    @ManyToOne
    private User user;
    private LocalDate dateOfCreate;


    @PrePersist
    public void init(){
        dateOfCreate = LocalDate.now();
    }
}
