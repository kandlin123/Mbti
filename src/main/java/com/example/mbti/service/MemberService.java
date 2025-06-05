package com.example.mbti.service;

import com.example.mbti.domain.Member;
import com.example.mbti.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findByUserid(String userid) {
        return memberRepository.findByUserid(userid).orElse(null);
    }
}
