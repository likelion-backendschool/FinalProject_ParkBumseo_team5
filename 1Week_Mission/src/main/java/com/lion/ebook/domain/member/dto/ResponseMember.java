package com.lion.ebook.domain.member.dto;

import com.lion.ebook.domain.member.domain.Auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class ResponseMember {
    private long id;
    private String username;
    private String email;
    private String nickname;
    private Auth auth;

}
