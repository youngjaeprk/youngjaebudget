package com.budget.Controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class IncomeForm {

    @NotEmpty(message = "카테고리를 선택해주세요")
    private String category;

    @NotNull(message = "금액을 입력해주세요")
    private BigDecimal amount;

    @NotNull(message = "날짜를 선택해주세요")
    private LocalDate date;

    private String memo;  // 메모는 선택사항이므로 validation 없음
}