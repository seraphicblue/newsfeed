package com.example.reservation.member.presentation;

import com.example.reservation.member.presentation.request.LoginRequest;
import com.example.reservation.member.presentation.request.SignupRequest;
import com.example.reservation.member.presentation.request.UpdateMemberInfoRequest;
import com.example.reservation.member.presentation.request.UpdatePasswordRequest;
import com.example.reservation.member.domain.MemberService;
import com.example.reservation.member.presentation.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;


    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        memberService.signup(request.getEmail(),request.getName(),request.getPassword(),
                request.getProfile(),request.getGreeting());
        return ResponseEntity.ok().build();
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        memberService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok().build();
    }

    // 비밀번호 변경
    @PutMapping("/{memberId}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable(name = "memberId") Long memberId,
                                               @RequestBody UpdatePasswordRequest request) {
        memberService.updatePassword(memberId, request.getCurrentPassword(),
                                        request.getNewPassword());
        return ResponseEntity.ok().build();
    }


    // 이름, 프로필 이미지, 인사말 업데이트
    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updateMemberInfo(@PathVariable(name = "memberId") Long memberId,
                                                 @RequestBody UpdateMemberInfoRequest request) {
        memberService.updateMemberInfo(memberId, request.getName(),
                                        request.getProfile(), request.getGreeting());
        return ResponseEntity.ok().build();
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        memberService.logout(request);
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }


}
