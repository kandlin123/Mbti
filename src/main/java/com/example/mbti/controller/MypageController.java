package com.example.mbti.controller;

import com.example.mbti.domain.Board;
import com.example.mbti.domain.Comment;
import com.example.mbti.domain.Member;
import com.example.mbti.repository.BoardRepository;
import com.example.mbti.repository.CommentRepository;
import com.example.mbti.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/mypage")
    public String mypage(Model model, Principal principal) {
        String loginId = principal.getName();
        Member member = memberRepository.findByUserid(loginId).orElse(null); // ✅ 수정

        if (member == null) {
            return "redirect:/login"; // 또는 "error/404"
        }

        List<Board> myPosts = boardRepository.findAll().stream()
                .filter(post -> post.getWriter().equals(member.getNickname()))
                .toList();

        List<Comment> myComments = commentRepository.findAll().stream()
                .filter(c -> c.getWriter().equals(member.getNickname()))
                .toList();

        model.addAttribute("member", member);
        model.addAttribute("myPosts", myPosts);
        model.addAttribute("myComments", myComments);
        return "mypage";
    }
}
