package com.example.mbti.repository;

import com.example.mbti.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBnoOrderByCreatedAtAsc(Long bno); // 해당 게시글의 댓글
}
