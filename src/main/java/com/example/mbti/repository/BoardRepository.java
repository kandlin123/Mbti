package com.example.mbti.repository;

import com.example.mbti.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByAuthorOrderBycreatedAtDesc(String userid);
}
