package com.lion.ebook.domain.member.controller;
import com.lion.ebook.domain.member.dto.RequestJoin;
import com.lion.ebook.domain.member.dto.RequestModify;
import com.lion.ebook.domain.member.dto.ResponseMember;
import com.lion.ebook.domain.member.dto.SignForm;
import com.lion.ebook.domain.member.entity.Member;
import com.lion.ebook.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/join")
    public String signup(Model model) {
        model.addAttribute("requestJoin", new RequestJoin());
        return "/member/join";
    }

    @PostMapping("/member/join")
    public ResponseEntity<ResponseMember> signup(@RequestBody RequestJoin requestJoin) {

        ResponseMember responseMember = memberService.save(requestJoin);
        return ResponseEntity.ok(responseMember);
    }

    @GetMapping("/member/modify")
    public String modify(Model model) {
        model.addAttribute("requestModify", new RequestModify());
        return "/member/modify";
    }

    @PostMapping("/member/modify")
    public ResponseEntity<ResponseMember> modify (
            @ModelAttribute("requestModify") RequestModify requestModify
            , @AuthenticationPrincipal Member member
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ResponseMember responseMember = memberService.update(username, requestModify);
        return ResponseEntity.ok(responseMember);
    }

}