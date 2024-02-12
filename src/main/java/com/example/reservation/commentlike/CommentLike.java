package com.example.reservation.commentlike;

import com.example.reservation.activity.Activity;
import com.example.reservation.activity.ActivityType;
import com.example.reservation.comment.Comment;
import com.example.reservation.member.Member;
import com.example.reservation.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "commentlike")
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id", updatable = false)
    private Long commentLikeId; // 코멘트 좋아요의 식별자

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment; // 좋아요를 누른 코멘트

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 좋아요를 누른 사용자

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;
    @Builder
    public CommentLike(Comment comment, Member member, ActivityType activityType) {
        this.comment = comment;
        this.member = member;
        this.activityType = activityType;
    }
}

