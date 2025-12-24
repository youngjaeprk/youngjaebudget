package com.budget.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Income {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;

    private String category;
    private BigDecimal amount;
    private LocalDate date;
    private String memo;
}