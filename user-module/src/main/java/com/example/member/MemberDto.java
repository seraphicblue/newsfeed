package com.example.member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {
    private Long memberId;
    private String email;
    private String name;
    private String profile;
    private String greeting;
    @Builder
    public MemberDto(Long memberId, String email, String name,  String profile, String greeting) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.greeting = greeting;
    }
}
