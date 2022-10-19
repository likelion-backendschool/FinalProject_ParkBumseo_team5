package com.lion.ebook.domain.member.controller;
import com.lion.ebook.domain.member.dto.*;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/member/findUsername")
    public String join(Model model) {
        model.addAttribute("requestFindUsername", new RequestFindUsername());
        return "/member/findUsername";
    }

    @PostMapping("/member/findUsername")
    public String join (
            @ModelAttribute("requestFindUsername") RequestFindUsername requestFindUsername
            , RedirectAttributes re
    ) {
        ResponseMember responseMember = memberService.findUsername(requestFindUsername);
        re.addFlashAttribute("responseMember", responseMember);

        return "redirect:/member/findResult";
    }

    @GetMapping("/member/findResult")
    public String result() {
        return "/member/findResult";
    }

}