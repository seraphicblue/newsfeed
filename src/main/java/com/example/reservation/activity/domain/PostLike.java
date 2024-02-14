package com.example.reservation.activity.domain;

import com.example.reservation.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "postlike")
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_like_id", updatable = false)
    private Long postLikeId; // 변경된 ID 필드명

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // 좋아요를 누른 게시물

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 좋아요를 누른 사용자

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Builder
    public PostLike(Post post, Member member, ActivityType activityType) {
        this.post = post;
        this.member = member;
        this.activityType = activityType;
    }
}




