package com.budget.Controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    @NotEmpty(message = "아이디를 입력해주세요")
    private Long loginId;  // Member 엔티티에 맞춰서

    @NotEmpty(message = "비밀번호를 입력해주세요")
    private String password;
}
