package com.example.mbti.controller;

import com.example.mbti.repository.MemberRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mypage")
public class PreferenceController {

    @Autowired
    private MemberRepository memberRepository; // UserService 대신 MemberRepository 사용

    // 현재 로그인된 사용자의 ID를 String 타입으로 가져오는 메서드
    // 실제 애플리케이션에서는 세션, 커스텀 인증 방식 등에서 이 ID를 가져와야 합니다.
    private String getCurrentUserId() {
        // 이 부분은 실제 로그인 시스템에 따라 구현되어야 합니다.
        // 예를 들어, 세션에서 사용자 ID를 String 형태로 가져오거나, 다른 인증 메커니즘을 통해 얻을 수 있습니다.
        // 현재는 예시를 위해 "user123"으로 하드코딩합니다.
        return "user123";
    }

    // 마이페이지 선호도 설정 페이지 로드
    @GetMapping("/preferences")
    public String showPreferences(Model model) {
        model.addAttribute("activeMenu", "preferences");

        // --- 테스트를 위한 임시 장르 데이터 (실제로는 DB에서 가져옴) ---
        List<List<String>> movieGenres = new ArrayList<>();
        movieGenres.add(Arrays.asList("로맨스", "로맨스"));
        movieGenres.add(Arrays.asList("공포", "공포"));
        movieGenres.add(Arrays.asList("코믹", "코믹"));
        movieGenres.add(Arrays.asList("액션", "액션"));

        List<List<String>> dramaGenres = new ArrayList<>();
        dramaGenres.add(Arrays.asList("로맨스", "로맨스"));
        dramaGenres.add(Arrays.asList("공포", "공포"));
        dramaGenres.add(Arrays.asList("코믹", "코믹"));
        dramaGenres.add(Arrays.asList("스릴러", "스릴러"));

        List<List<String>> foodGenres = new ArrayList<>();
        foodGenres.add(Arrays.asList("한식", "한식"));
        foodGenres.add(Arrays.asList("중식", "중식"));
        foodGenres.add(Arrays.asList("일식", "일식"));
        foodGenres.add(Arrays.asList("양식", "양식"));

        model.addAttribute("movieGenres", movieGenres);
        model.addAttribute("dramaGenres", dramaGenres);
        model.addAttribute("foodGenres", foodGenres);

        // --- 사용자 선호도 데이터 로드 (현재 사용자 ID를 통해 DB에서 가져옴) ---
        String userId = getCurrentUserId(); // String 타입 userId 사용
        // memberRepository.findById()를 사용하여 User 엔티티 조회
        User currentUser = (User) memberRepository.findByUserid(userId) // String 타입 userId로 조회
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Set<String> userPreferences = new HashSet<>(currentUser.getPreferences());

        model.addAttribute("preferences", userPreferences);

        return "mypage_3"; // html 렌더링
    }
}