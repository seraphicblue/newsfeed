package com.example.reservation.member.request;

import lombok.Getter;

@Getter
public class UpdateMemberInfoRequest {
    private Long memberId;
    private String name;
    private String profile;
    private String greeting;
}

