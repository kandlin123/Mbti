package com.example.mbti.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
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

    private LocalDateTime regdate; // 가입일
    private LocalDateTime moddate; // 수정일

    @PrePersist // 엔티티 저장 전 실행
    protected void onCreate() {
        this.regdate = LocalDateTime.now();
        this.moddate = LocalDateTime.now();
    }

    @PreUpdate // 엔티티 업데이트 전 실행
    protected void onUpdate() {
        this.moddate = LocalDateTime.now();
    }
}
