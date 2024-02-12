package com.example.reservation.member;

import com.example.reservation.jwt.JwtUtil;
import com.example.reservation.member.request.LoginRequest;
import com.example.reservation.member.request.SignupRequest;
import com.example.reservation.member.request.UpdateMemberInfoRequest;
import com.example.reservation.member.request.UpdatePasswordRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final AuthenticationManager authenticationManager;
    private final MemberService memberService;

    public MemberController(AuthenticationManager authenticationManager, MemberService memberService) {
        this.authenticationManager = authenticationManager;
        this.memberService = memberService;
    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        memberService.signup(request.getEmail(),request.getName(),
                request.getPassword(), request.getGreeting(),request.getProfile());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 인증 성공 후, 사용자의 username을 기반으로 토큰 생성
            String token = JwtUtil.createToken(loginRequest.getEmail());

            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }

    // 비밀번호 변경
    @PutMapping("/{memberId}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable(name = "memberId") Long memberId, @RequestBody UpdatePasswordRequest request) {
        memberService.updatePassword(memberId, request.getCurrentPassword(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }


    // 이름, 프로필 이미지, 인사말 업데이트
    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updateMemberInfo(@PathVariable(name = "memberId") Long memberId, @RequestBody UpdateMemberInfoRequest request) {
        memberService.updateMemberInfo(memberId, request.getName(), request.getProfile(), request.getGreeting());
        return ResponseEntity.ok().build();
    }
    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            String token = extractTokenFromHeader(request);

            // null로 설정하여 토큰만료시킵니다.
            token = null;

            return ResponseEntity.ok().body("로그아웃 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("로그아웃 실패: " + e.getMessage());
        }
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        //  "Authorization" 헤더에서 토큰을 가져오기.
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // "Bearer " 이후의 토큰 값만 추출합니다.
        }
        return null;
    }


}
