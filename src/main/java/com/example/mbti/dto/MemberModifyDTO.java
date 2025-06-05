package com.example.mbti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberModifyDTO {
    private String userid; // Hidden input으로 넘어오는 userId (수정할 회원을 식별)
    private String email; // 변경될 수 있는 이메일
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
    private String mbti;
    private String nickname;
    private String gender;
}