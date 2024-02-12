package com.example.reservation.member;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(String email, String name, String password, String profile, String greeting) {
        memberRepository.save(new Member(email, name, passwordEncoder.encode(password), profile, greeting));
    }
    public void updatePassword(Long memberId, String currentPassword, String newPassword) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("멤버가 없습니다: " + memberId));

        if (!passwordEncoder.matches(currentPassword, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(newPassword);
        member.setPassword(encodedPassword);
        memberRepository.save(member);
    }

    public void updateMemberInfo(Long memberId, String name, String profile, String greeting) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("멤버가 없습니다: " + memberId));

        member.updateInfo(name, profile, greeting);

        memberRepository.save(member);
    }

}
