package com.lion.ebook.domain.member.controller;
import com.lion.ebook.domain.member.dto.RequestJoin;
import com.lion.ebook.domain.member.dto.ResponseMember;
import com.lion.ebook.domain.member.dto.SignForm;
import com.lion.ebook.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
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
}