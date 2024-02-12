package com.example.reservation.comment;

import com.example.reservation.activity.ActivityType;
import com.example.reservation.member.Member;
import com.example.reservation.member.MemberRepository;
import com.example.reservation.post.Post;
import com.example.reservation.post.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.reservation.activity.ActivityType.COMMENT;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public Comment createComment(Long memberId, Long postId, String content) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을수 없습니다"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("포스트의 아이디를 찾을 수 없습니다. " + postId));

        // 새 Comment 객체를 생성합니다.
        Comment comment = Comment.builder()
                .activityType(COMMENT)
                .member(member)
                .content(content)
                .post(post)
                .build();

        // Comment 객체를 데이터베이스에 저장합니다.
        return commentRepository.save(comment);
    }
}
