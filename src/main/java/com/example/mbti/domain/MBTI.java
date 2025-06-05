package com.example.mbti.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MBTI")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MBTI {

    @Id
    @Column(name = "MBTINum")
    private Integer mbtiNum;

    @Column(name = "MBTIType")
    private String mbtiType;
}
