package com.ll.exam.final__2022_10_08.app.rebate.controller;

import com.ll.exam.final__2022_10_08.app.rebate.entity.RebateOrderItem;
import com.ll.exam.final__2022_10_08.app.rebate.entity.form.RebateForm;
import com.ll.exam.final__2022_10_08.app.rebate.service.RebateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adm/rebate")
public class AdminRebateController {
    private final RebateService rebateService;

    // 정산 데이터 생성폼
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @GetMapping("/makeData")
    public String showMakeData() {
        return "/adm/rebate/makeData";
    }

    // 정산 데이터 생성
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @PostMapping("/makeData")
    @ResponseBody
    public String makeData(RebateForm rebateForm) {

        int year = rebateForm.getYear();
        int month = rebateForm.getMonth();
        String yearMonth = year+month+""; // 추후에 수정해야 됨.
        rebateService.makeData(yearMonth);

        return "성공";
    }
}
