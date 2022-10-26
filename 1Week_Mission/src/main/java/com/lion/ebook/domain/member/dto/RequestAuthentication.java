package com.lion.ebook.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAuthentication {
    private String username;
    private String password;
}
