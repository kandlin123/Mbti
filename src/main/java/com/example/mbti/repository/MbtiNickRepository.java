package com.example.mbti.repository;

import com.example.mbti.domain.Mbtinicktable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MbtiNickRepository extends JpaRepository<Mbtinicktable, Long> {
    List<Mbtinicktable> findByMbtinum(int mbtinum);  // ✅ 이름 & 타입 정확히 일치시킴
}
