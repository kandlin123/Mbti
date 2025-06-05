package com.example.mbti;

import com.example.mbti.domain.Member;
import com.example.mbti.dto.MemberInfoDTO;
import com.example.mbti.repository.MemberRepository;
import com.example.mbti.service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional // 각 테스트가 끝난 후 롤백되어 DB 상태가 유지됨
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("DB에 저장된 회원 정보가 정상적으로 조회된다")
    void getMemberInfo_success() {

        MemberInfoDTO result = memberService.getMemberInfo("test0602");

        // then
        assertNotNull(result);
        assertEquals("test0602", result.getUserid());


    }

    @Test
    @DisplayName("DB에 없는 회원 조회 시 예외가 발생한다")
    void getMemberInfo_userNotFound() {
        // given
        String nonExistentUserId = "nouser";

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            memberService.getMemberInfo(nonExistentUserId);
        });

        assertEquals("회원을 찾을 수 없습니다.", exception.getMessage());
    }
}