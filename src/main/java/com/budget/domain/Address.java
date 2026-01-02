package com.budget.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String city;          // 시/도 + 시/군/구 (예: "서울특별시 강남구")
    private String street;        // 도로명 주소 (예: "테헤란로 123")
    private String zipcode;       // 우편번호 (예: "06234")
    private String detailAddress; // 상세주소 (예: "101동 1001호")

    public Address(String city, String street, String zipcode, String detailAddress) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.detailAddress = detailAddress;
    }
}