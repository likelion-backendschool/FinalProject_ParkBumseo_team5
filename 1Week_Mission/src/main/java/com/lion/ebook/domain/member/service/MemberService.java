package com.lion.ebook.domain.member.service;


import com.lion.ebook.domain.member.domain.Auth;
import com.lion.ebook.domain.member.dto.RequestJoin;
import com.lion.ebook.domain.member.dto.RequestModify;
import com.lion.ebook.domain.member.dto.ResponseMember;
import com.lion.ebook.domain.member.dto.SignForm;
import com.lion.ebook.domain.member.entity.Member;
import com.lion.ebook.domain.member.exception.MemberNotFoundException;
import com.lion.ebook.domain.member.repository.MemberRepository;
import com.lion.ebook.domain.member.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;
    private final PasswordEncoder passwordEncoder;

    public ResponseMember save(RequestJoin requestJoin) {

        Auth auth = requestJoin.getAuth();
        requestJoin.setAuth(auth);
        requestJoin.setPassword(passwordEncoder.encode(requestJoin.getPassword()));
        Member member = memberRepository.save(requestJoin.toEntity());
        return memberUtil.getResponse(member);
    }

    public ResponseMember update(String username, RequestModify requestModify) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Member is Not Found"));
        member.setEmail(requestModify.getEmail());
        member.setNickname(requestModify.getNickname());
        return memberUtil.getResponse(member);
    }


    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("해당 멤버는 존재하지 않습니다."));
    }
}
