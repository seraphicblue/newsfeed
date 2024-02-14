package com.example.reservation.activity.domain;

import com.example.reservation.activity.Infrastructure.CommentLikeRepository;
import com.example.reservation.activity.Infrastructure.CommentRepository;
import com.example.reservation.member.domain.Member;
import com.example.reservation.member.memberrepository.MemberRepository;
import com.example.reservation.newsfeed.domain.NewsfeedService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.reservation.activity.domain.ActivityType.COMMENT_LIKE;
@RequiredArgsConstructor
@Service
public class CommentLikeService {
    private final CommentLikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final NewsfeedService newsfeedService;
    private final FollowService followservice;

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

        newsfeedService.addNotificationToNewsfeed(comment.getMember(),member ,newLike.getActivityType());
        //그렇죠 뉴스피드의 역할을 좋아요 좋아요를 누른사람과 해당 댓글을 쓴사람 이렇게만

        // Like 객체를 반환합니다.
        return newLike;
    }
}
