package com.example.reservation.activity.domain;

import com.example.reservation.activity.Infrastructure.CommentRepository;
import com.example.reservation.member.domain.Member;
import com.example.reservation.member.memberrepository.MemberRepository;
import com.example.reservation.newsfeed.domain.NewsfeedService;
import com.example.reservation.activity.Infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.reservation.activity.domain.ActivityType.COMMENT;
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final NewsfeedService newsfeedService;

    public Comment createComment(Long memberId, Long postId, String content) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("포스트의 아이디를 찾을 수 없습니다. " + postId));


        Comment comment = Comment.builder()
                .activityType(COMMENT)
                .member(member)
                .content(content)
                .post(post)
                .build();

        // Comment 객체를 데이터베이스에 저장합니다.
        comment = commentRepository.save(comment);

        // 뉴스피드 엔티티 생성
        newsfeedService.addNotificationToNewsfeed(post.getMember(), member, comment.getActivityType());
       
        return comment;
    }
}
