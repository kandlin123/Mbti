package com.example.mbti.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mbtinicktablev2")
public class Mbtinicktable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int mbtinum;

    private String prefix;

    private String suffix1;

    private String suffix2;
}
