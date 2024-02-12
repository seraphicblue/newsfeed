package com.example.reservation.commentlike;

import com.example.reservation.comment.Comment;
import com.example.reservation.comment.CommentRepository;
import com.example.reservation.follow.Follow;
import com.example.reservation.follow.FollowService;
import com.example.reservation.member.Member;
import com.example.reservation.member.MemberRepository;
import com.example.reservation.newsfeed.NewsfeedService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.reservation.activity.ActivityType.COMMENT_LIKE;

@Service
public class CommentLikeService {
    private final CommentLikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final NewsfeedService newsfeedService;
    private final FollowService followservice;

    public CommentLikeService(CommentLikeRepository likeRepository, CommentRepository commentRepository, MemberRepository memberRepository, NewsfeedService newsfeedService, FollowService followservice) {
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.newsfeedService = newsfeedService;
        this.followservice = followservice;
    }

    public CommentLike likeComment(Long memberId, Long commentId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("코멘트를 찾을 수 없습니다. " + commentId));

        // 댓글 엔티티 생성 후 저장
        CommentLike newLike = CommentLike.builder()
                .comment(comment)
                .member(member)
                .activityType(COMMENT_LIKE)
                .build();
        newLike = likeRepository.save(newLike);

        // 뉴스피드 엔티티 생성 후 저장

        newsfeedService.addNotificationToNewsfeed( comment.getMember(),member ,newLike.getActivityType());
        //그렇죠 뉴스피드의 역할을 좋아요 좋아요를 누른사람과 해당 댓글을 쓴사람 이렇게만

        // Like 객체를 반환합니다.
        return newLike;
    }
}
