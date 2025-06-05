package com.example.mbti.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Board") // 기존 대문자 테이블 이름 유지
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bno")
    private Long bno;

    @ManyToOne
    @JoinColumn(name = "userid")  // 외래키: Member 테이블의 UserId
    private Member user;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "writer")  // 글쓴이 닉네임
    private String writer;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "readcount")
    private int readCount;

    @Column(name = "media")  // 이미지 경로
    private String media;

    @ManyToOne
    @JoinColumn(name = "mbtinum")  // 외래키: MBTI 테이블의 MBTINum
    private MBTI mbti;

    @Column(name = "writermbtitype")
    private String writerMbtiType;

    @Column(name = "writernickname")
    private String writerNickname;

    // 자동 시간 세팅
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
