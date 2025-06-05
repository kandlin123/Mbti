//package com.example.mbti.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration // Spring 설정 클래스임을 명시
//@EnableWebSecurity // Spring Security 활성화
//public class SecurityConfig {
//
//    // 1. PasswordEncoder 빈 등록: 비밀번호 암호화를 담당
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // BCryptPasswordEncoder는 가장 널리 사용되는 비밀번호 해싱 알고리즘 중 하나
//        return new BCryptPasswordEncoder();
//    }
//}