package com.innup.startupinvest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class StartUpComment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    private LocalDate dateOfCreate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private StartUp startUp;


    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "startUpComment",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReplyComment> replyCommentList;

    @PrePersist
    public void init(){
        dateOfCreate = LocalDate.now();
    }

}
