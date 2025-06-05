package com.example.mbti.service;

import com.example.mbti.domain.Board;
import com.example.mbti.domain.Member;
import com.example.mbti.repository.BoardRepository;
import com.example.mbti.repository.CommentRepository;
import com.example.mbti.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // 데이터 조회 작업은 읽기 전용 트랜잭션으로 설정
public class MyPageService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public MyPageService(MemberRepository memberRepository, BoardRepository repository, CommentRepository commentRepository) {
        this.memberRepository = memberRepository;
        this.boardRepository = repository;
        this.commentRepository = commentRepository;
    }

    public Optional<Member> getMemberById(Member userid) {
        return memberRepository.findByUserid(userid.getUserid()); // ID로 회원 정보 조회
    }

    public List<Board> getMyPosts(Member userid) {
        return boardRepository.findByAuthorOrderBycreatedAtDesc(userid.getUserid()); // 특정 회원의 게시글 조회
    }

    public List<Board> getMyComments(Member userid) {
        return commentRepository.findByAuthorOrderBycreatedAtDesc(userid.getUserid()); // 특정 회원의 댓글 조회
    }

//    @Transactional
//    public void updateprofile(Member userid) {
//
//    }

    @Transactional
    public void deleteMember(Long memberId) {
        // 실제 애플리케이션에서는 회원 탈퇴 시 관련 게시글/댓글 처리 로직도 필요합니다.
        memberRepository.deleteById(memberId); // 회원 탈퇴
    }

    // 다른 마이페이지 기능들을 여기에 추가할 수 있습니다.
}