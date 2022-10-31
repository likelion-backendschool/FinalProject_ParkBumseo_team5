package com.lion.ebook.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFindPassword {
    private String username;
    private String email;
}
