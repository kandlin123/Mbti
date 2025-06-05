package com.example.mbti.service;

import com.example.mbti.domain.MBTI;
import com.example.mbti.domain.Mbtinicktable;
import com.example.mbti.repository.MbtiNickRepository;
import com.example.mbti.repository.MbtiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class NicknameService {

    @Autowired
    private MbtiRepository mbtiRepository;

    @Autowired
    private MbtiNickRepository mbtinickRepository;

    public String generateNickname(String mbtiType) {
        Optional<MBTI> mbtiOpt = mbtiRepository.findByMbtiType(mbtiType);  // ✅ 수정

        if (mbtiOpt.isEmpty()) return "MBTI없음";

        int mbtiNum = mbtiOpt.get().getMbtiNum();  // ✅ 수정

        List<Mbtinicktable> nicknameList = mbtinickRepository.findByMbtinum(mbtiNum);

        if (nicknameList.isEmpty()) return "닉네임 없음";

        Mbtinicktable random = nicknameList.get(new Random().nextInt(nicknameList.size()));

        String suffix = new Random().nextBoolean() ? random.getSuffix1() : random.getSuffix2();

        return random.getPrefix() + suffix;
    }
}
