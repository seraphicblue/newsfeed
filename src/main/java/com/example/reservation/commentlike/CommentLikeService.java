package com.example.reservation.commentlike;

import com.example.reservation.comment.Comment;
import com.example.reservation.comment.CommentRepository;
import com.example.reservation.member.Member;
import com.example.reservation.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.reservation.activity.ActivityType.COMMENT_LIKE;

@Service
public class CommentLikeService {
    private final CommentLikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public CommentLikeService(CommentLikeRepository likeRepository, CommentRepository commentRepository, MemberRepository memberRepository) {
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
    }


    public CommentLike likeComment(Long memberId, Long commentId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을수 없습니다"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("코멘트를 찾을 수 없습니다. " + commentId));

        // 새로운 Like 객체를 생성하고 사용자와 포스트를 설정합니다.
        CommentLike newcLike = CommentLike.builder()
                .comment(comment)
                .member(member)
                .activityType(COMMENT_LIKE)
                .build();

        // Like 객체를 데이터베이스에 저장합니다.
        return likeRepository.save(newcLike);
    }
}
