package com.lion.ebook.domain.member.dto;

import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignForm {

    @NotEmpty(message = "아이디를 입력해주세요.")
    private String username;
    @NotEmpty(message = "비빌번호를 입력해주세요.")
    private String password;
    @NotEmpty(message = "비밀번호 확인을 입력해주세요.")
    private String passwordConfirm;
    @Email
    private String email;
    private String nickname;
}