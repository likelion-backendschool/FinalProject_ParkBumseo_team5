package com.lion.ebook.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestModify {
    private String email;
    private String nickname;
}
