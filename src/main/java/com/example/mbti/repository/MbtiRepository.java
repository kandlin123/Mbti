package com.example.mbti.repository;

import com.example.mbti.domain.MBTI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MbtiRepository extends JpaRepository<MBTI, Integer> {

    // mbtiNum으로 조회
    Optional<MBTI> findByMbtiNum(Integer mbtiNum);

    // mbtiType으로 조회
    Optional<MBTI> findByMbtiType(String mbtiType);

}
