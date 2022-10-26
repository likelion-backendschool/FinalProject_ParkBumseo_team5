package com.lion.ebook.domain.member.controller;

import com.lion.ebook.domain.member.dto.RequestAuthentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class MemberAuthenticationController {
    @GetMapping("/member/login")
    public String login(Model model) {
        model.addAttribute("requestAuthentication", new RequestAuthentication());
        return "/member/login";
    }

    @GetMapping("/member/logout")
    public String logout() {
        return "redirect:/member/login";
    }
}
