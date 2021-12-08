package com.example.mycms.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PageImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Page page;

    @Column
    private String fileName;

    @Column(unique = true)
    private String identity;

    @Column
    private String path;
}
