package com.example.mbti.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    private String userid;

    @Column(nullable = false)
    private String userpw;

    @Column(nullable = false)
    private String nickname;

    private String email;
    private String role; // "USER", "ADMIN" 등
}
