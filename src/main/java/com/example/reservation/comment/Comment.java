package com.example.reservation.comment;


import com.example.reservation.activity.Activity;
import com.example.reservation.activity.ActivityType;
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
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", updatable = false)
    private Long commentId;

    @Column(name = "content", nullable = false)
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 댓글 작성자

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Builder
    public Comment(String content, Post post, Member member, ActivityType activityType) {
        this.activityType = activityType;
        this.content = content;
        this.post = post;
        this.member = member;
    }
}
