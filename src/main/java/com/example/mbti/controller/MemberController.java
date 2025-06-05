package com.example.mbti.controller;

import com.example.mbti.domain.Member;
import com.example.mbti.repository.MemberRepository;
import com.example.mbti.service.NicknameService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final NicknameService nicknameService;

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String userid,
                        @RequestParam String userpw,
                        HttpSession session,
                        Model model) {

        Optional<Member> optionalMember = memberRepository.findByUserid(userid);

        if (optionalMember.isEmpty()) {
            model.addAttribute("errorMessage", "아이디가 존재하지 않습니다."); // ✅ 작은따옴표 제거
            return "login";
        }

        Member member = optionalMember.get();
        if (!member.getUserpw().equals(userpw)) {
            model.addAttribute("errorMessage", "비밀번호가 틀렸습니다."); // ✅ 작은따옴표 제거
            return "login";
        }


        session.setAttribute("loginUser", member);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // ← templates/register.html 보여주기
    }

    @PostMapping("/register")
    public String register(Member member) {
        memberRepository.save(member); // 비밀번호 암호화는 개발 후 적용
        return "redirect:/login";
    }
}
