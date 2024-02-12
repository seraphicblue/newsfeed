package com.example.reservation.comment;

import com.example.reservation.activity.ActivityType;
import com.example.reservation.comment.CommentRepository;
import com.example.reservation.member.Member;
import com.example.reservation.member.MemberRepository;
import com.example.reservation.newsfeed.NewsfeedService;
import com.example.reservation.post.Post;
import com.example.reservation.post.PostRepository;
import org.springframework.stereotype.Service;

import static com.example.reservation.activity.ActivityType.COMMENT;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final NewsfeedService newsfeedService;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, MemberRepository memberRepository, NewsfeedService newsfeedService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.newsfeedService = newsfeedService;
    }

    public Comment createComment(Long memberId, Long postId, String content) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("포스트의 아이디를 찾을 수 없습니다. " + postId));

        // 새 Comment 객체를 생성합니다.
        Comment comment = Comment.builder()
                .activityType(COMMENT)
                .member(member)
                .content(content)
                .post(post)
                .build();

        // Comment 객체를 데이터베이스에 저장합니다.
        comment = commentRepository.save(comment);

        // 뉴스피드 엔티티 생성
        newsfeedService.addNotificationToNewsfeed(post.getMember(), member,comment.getActivityType());
       
        return comment;
    }
}
