package com.example.mbti.controller;

import com.example.mbti.service.NicknameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nickname")
public class NicknameController {

    @Autowired
    private NicknameService nicknameService;

    @GetMapping("/generate")
    public ResponseEntity<String> generate(@RequestParam String mbti) {
        String nickname = nicknameService.generateNickname(mbti);
        return ResponseEntity.ok(nickname);
    }
}

