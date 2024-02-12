package com.example.reservation.activity;

import com.example.reservation.comment.Comment;
import com.example.reservation.commentlike.CommentLike;
import com.example.reservation.follow.Follow;
import com.example.reservation.member.Member;
import com.example.reservation.post.Post;
import com.example.reservation.postlike.PostLike;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private ActivityType activityType;

    // 다양한 활동 유형에 대한 필드 추가
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "follow_id")
    private Follow follow;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "post_like_id")
    private PostLike postLike;

    @ManyToOne
    @JoinColumn(name = "comment_like_id")
    private CommentLike commentLike;


    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
