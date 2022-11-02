package com.ll.exam.final__2022_10_08.app.rebate.entity.form;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RebateForm {
    @NotNull(message = "년도를 선택해주세요.")
    private int year;
    @NotNull(message = "월을 선택해주세요.")
    private int month;
}
