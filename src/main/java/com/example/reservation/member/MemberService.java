package com.example.reservation.member;

import com.example.reservation.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String signup(String email, String name, String password,
                         String profile, String greeting) {
        // 회원 가입 처리
        Member newMember = new Member(
                email,
                name,
                password,
                profile,
                greeting
        );

        memberRepository.save(newMember);

        return "회원 가입이 성공했습니다.";
    }
    public String login(String email, String password) {
        try {
            // 사용자 인증
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            // SecurityContext에 인증 정보 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 인증 성공 후, 사용자의 email을 기반으로 토큰 생성
            return JwtUtil.createToken(email);
        } catch (Exception e) {
            throw new AuthenticationServiceException("로그인에 실패했습니다: " + e.getMessage());
        }
    }

    public void updatePassword(Long memberId, String currentPassword, String newPassword) {

        Member member = memberRepository.findByIdOrThrow(memberId);

        if (!passwordEncoder.matches(currentPassword, newPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(newPassword);
        member.setPassword(encodedPassword);
        memberRepository.save(member);
    }

    public void updateMemberInfo(Long memberId, String name, String profile, String greeting) {
        Member member = memberRepository.findByIdOrThrow(memberId);

        member.updateInfo(name, profile, greeting);

        memberRepository.save(member);
    }

    public void logout(HttpServletRequest request) {
        try {
            String token = extractTokenFromHeader(request);
            token = null;
        } catch (Exception e) {
            throw new RuntimeException("로그아웃 실패: " + e.getMessage());
        }
    }

    // Authorization 헤더에서 토큰을 추출하는 메소드
    private String extractTokenFromHeader(HttpServletRequest request) {
        // "Authorization" 헤더에서 토큰을 가져오기.
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // "Bearer " 이후의 토큰 값만 추출합니다.
        }
        return null;
    }


}




