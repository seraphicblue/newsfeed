package com.example.reservation.post;

import com.example.reservation.activity.ActivityType;
import com.example.reservation.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class) // 엔티티 리스너 등록
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 포스트 작성자

    @Column(name = "content", nullable = false)
    private String content; // 포스트 내용

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @CreatedDate // 생성 시간 자동 설정
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 업데이트 시간 자동 설정
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Post(Member member, String content, ActivityType activityType) {
        this.member = member;
        this.content = content;
        this.activityType = activityType;
    }
}
