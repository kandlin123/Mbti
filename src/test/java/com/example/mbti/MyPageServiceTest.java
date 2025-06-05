package com.example.mbti;

import com.example.mbti.domain.Board;
import com.example.mbti.domain.Comment;
import com.example.mbti.domain.Member;
import com.example.mbti.repository.BoardRepository;
import com.example.mbti.repository.CommentRepository;
import com.example.mbti.repository.MemberRepository;
import com.example.mbti.service.MyPageService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test") // test 환경의 설정값 사용 (예: application-test.yml)
public class MyPageServiceTest {

    @Autowired
    private  MyPageService myPageService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Member testMember;

    @BeforeEach
    void setUp() {
        // 테스트용 회원 저장
        testMember = new Member();
        testMember.setUserid("testuser");
        testMember.setNickname("테스트유저");
        memberRepository.save(testMember);

        // 테스트용 게시글 저장
        Board post = new Board();
        post.setWriter("testuser");
        post.setTitle("테스트 게시글");
        post.setContent("내용입니다.");
        post.setCreatedAt(LocalDateTime.now());
        boardRepository.save(post);

        // 테스트용 댓글 저장
        Comment comment = new Comment();
        comment.setWriter("testuser");
        comment.setContent("테스트 댓글입니다.");
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Test
    void testGetMemberById() {
        Optional<Member> result = myPageService.getMemberById(testMember);
        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUserid());
    }

    @Test
    void testGetMyPosts() {
        List<Board> posts = myPageService.getMyPosts(testMember);
        assertFalse(posts.isEmpty());
        assertEquals("testuser", posts.get(0));
    }

    @Test
    void testGetMyComments() {
        List<Board> comments = myPageService.getMyComments(testMember);
        assertFalse(comments.isEmpty());
        assertEquals("testuser", comments.get(0));
    }
}

