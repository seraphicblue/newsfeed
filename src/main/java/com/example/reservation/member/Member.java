package com.example.reservation.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile")
    private String profile;

    @Column(name = "greeting")
    private String greeting;

    @Builder
    public Member(String email, String name, String password, String profile, String greeting) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.profile = profile;
        this.greeting = greeting;
    }
    public void updateInfo(String name, String profile, String greeting) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
        if (profile != null && !profile.isEmpty()) {
            this.profile = profile;
        }
        if (greeting != null && !greeting.isEmpty()) {
            this.greeting = greeting;
        }
    }


}
