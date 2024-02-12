package com.example.reservation.post;

import com.example.reservation.comment.CommentRepository;
import com.example.reservation.follow.FollowService;
import com.example.reservation.member.Member;
import com.example.reservation.member.MemberRepository;
import com.example.reservation.postlike.PostLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.reservation.activity.ActivityType.POST;


@Slf4j
@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public Post createPost(Long memberId, String content) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을수 없습니다"));
        Post newPost = Post.builder()
                .content(content)
                .member(member)
                .activityType(POST)
                .build(); // Post 클래스의 생성자 또는 빌더를 이용한 인스턴스 생성
        return postRepository.save(newPost);
    }


}


