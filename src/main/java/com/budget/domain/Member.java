package com.budget.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String name;

    // 월 수입 리스트 (월급, 부수입 등)
    @OneToMany(mappedBy = "member")
    private List<Income> incomes = new ArrayList<>();

    // 고정 지출 리스트 (월세, 통신비, 구독료 등)
    @OneToMany(mappedBy = "member")
    private List<FixedExpense> fixedExpenses = new ArrayList<>();

    // 변동 지출 리스트 (식비, 교통비, 쇼핑 등)
    @OneToMany(mappedBy = "member")
    private List<Transaction> transactions = new ArrayList<>();


    @Entity
    public class Income {
        @Id @GeneratedValue
        private Long id;

        @ManyToOne
        @JoinColumn(name = "member_id")
        private Member member;

        private String source; // "월급", "부수입", "용돈"
        private BigDecimal amount;
        private LocalDate receivedDate;
    }

    @Entity
    public class FixedExpense {
        @Id @GeneratedValue
        private Long id;

        @ManyToOne
        @JoinColumn(name = "member_id")
        private Member member;

        private String name; // "월세", "통신비", "넷플릭스"
        private BigDecimal amount;
        private int paymentDay; // 매월 몇일에 나가는지
    }

    @Entity
    public class Transaction {
        @Id @GeneratedValue
        private Long id;

        @ManyToOne
        @JoinColumn(name = "member_id")
        private Member member;

        private String category; // "식비", "교통", "쇼핑"
        private BigDecimal amount;
        private LocalDateTime transactionDate;
        private String memo;
    }
}