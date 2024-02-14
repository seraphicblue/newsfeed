package com.example.reservation.member.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
public class LoginResponse {
    private String email;
    private String token;
}
