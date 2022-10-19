package com.lion.ebook.domain.member.util;

import com.lion.ebook.domain.member.dto.ResponseMember;
import com.lion.ebook.domain.member.entity.Member;

public class MemberUtil {

    public ResponseMember getResponse(Member member) {
        return ResponseMember.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

}
