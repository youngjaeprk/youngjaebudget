package com.budget.Controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 아이디는 필수입니다")
    private String loginId;
    
    @NotEmpty(message = "회원 이름은 필수입니다")
    private String name;

    @NotEmpty(message = "비밀번호는 필수입니다")
    private String password;

    // Address 필드들 (카카오맵 API 사용)
    @NotEmpty(message = "주소는 필수입니다")
    private String city;          // 시/도 + 시/군/구

    @NotEmpty(message = "도로명 주소는 필수입니다")
    private String street;        // 도로명 주소

    @NotEmpty(message = "우편번호는 필수입니다")
    private String zipcode;       // 우편번호

    private String detailAddress; // 상세주소 (선택사항)
}