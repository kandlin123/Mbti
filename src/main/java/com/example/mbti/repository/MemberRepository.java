package com.example.mbti.repository;

import com.example.mbti.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserid(String userid);
    Optional<Member> findByNickname(String nickname);// ✅ 이게 꼭 Optional이어야 함

}

