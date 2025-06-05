package com.example.mbti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoDTO {
    private String userid;
    private String userpw;
    private String email;
    private String mbti;
    private String nickname;
    private String gender;
}