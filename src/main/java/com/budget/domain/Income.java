package com.budget.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Income {
    @Id @GeneratedValue
    @Column(name = "income_id") //PK 컬럼명 명시
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩 설정
    @JoinColumn(name = "member_id")      // FK 컬럼명 명시
    private Member member;

    private String category;
    private BigDecimal amount;
    private LocalDate date;
    private String memo;


}