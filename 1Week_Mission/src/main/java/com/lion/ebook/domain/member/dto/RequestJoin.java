package com.lion.ebook.domain.member.dto;

import com.lion.ebook.domain.member.domain.Auth;
import com.lion.ebook.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class RequestJoin {
    private String username;
    private String email;
    @Nullable
    private String nickname;
    private String password;
    private Auth auth;

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .email(email)
                .nickname(nickname)
                .password(password)
                .auth(auth)
                .build();
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
