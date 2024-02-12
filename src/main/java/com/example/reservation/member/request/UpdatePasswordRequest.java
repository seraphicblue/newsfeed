package com.example.reservation.member.request;

import lombok.Getter;

@Getter
public class UpdatePasswordRequest {
    private Long memberId;
    private String currentPassword;
    private String newPassword;
}
