package com.example.reservation.postlike;

import com.example.reservation.member.Member;
import com.example.reservation.member.MemberRepository;
import com.example.reservation.newsfeed.NewsfeedService;
import com.example.reservation.post.Post;
import com.example.reservation.post.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.reservation.activity.ActivityType.POST_LIKE;

@Service
public class PostLikeService {
    private final PostLikeRepository likeRepository;
    private final PostRepository postRepository;

    private final MemberRepository memberRepository;
    private final NewsfeedService newsfeedService;

    @Autowired
    public PostLikeService(PostLikeRepository likeRepository, PostRepository postRepository, MemberRepository memberRepository, NewsfeedService newsfeedService) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.newsfeedService = newsfeedService;
    }

    public PostLike likePost(Long memberId, Long postId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을수 없습니다"));
        // postId에 해당하는 Post를 확인합니다.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("포스트의 아이디를 찾을 수 없습니다. " + postId));

        // 새로운 Like 객체를 생성하고 사용자와 포스트를 설정합니다.
        PostLike newLike = PostLike.builder()
                .post(post)
                .member(member)
                .activityType(POST_LIKE)
                .build();


        newsfeedService.addNotificationToNewsfeed(post.getMember(), member, newLike.getActivityType());

        // Like 객체를 데이터베이스에 저장합니다.
        return likeRepository.save(newLike);
    }

}
