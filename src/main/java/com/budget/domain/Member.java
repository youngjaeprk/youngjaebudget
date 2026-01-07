package com.budget.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @NotEmpty
    private String password;


}