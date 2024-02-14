package com.example.reservation.member.presentation.request;

import lombok.Getter;

@Getter
public class SignupRequest {
    private String email;
    private String password;
    private String name;
    private String greeting;
    private String profile;
}
