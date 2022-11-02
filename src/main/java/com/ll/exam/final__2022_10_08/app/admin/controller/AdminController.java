package com.ll.exam.final__2022_10_08.app.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adm")
public class AdminController {

    @GetMapping("/home/main")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String homePage(){

        return "adm/home/main";
    }

}
