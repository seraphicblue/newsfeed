package com.example.reservation.activity.domain;

import com.example.reservation.activity.Infrastructure.PostLikeRepository;
import com.example.reservation.member.domain.Member;
import com.example.reservation.member.memberrepository.MemberRepository;
import com.example.reservation.newsfeed.domain.NewsfeedService;
import com.example.reservation.activity.Infrastructure.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.reservation.activity.domain.ActivityType.POST_LIKE;
@RequiredArgsConstructor
@Service
public class PostLikeService {

    private final PostLikeRepository likeRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final NewsfeedService newsfeedService;

    public PostLike likePost(Long memberId, Long postId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을수 없습니다"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("포스트의 아이디를 찾을 수 없습니다. "));


        PostLike newLike = PostLike.builder()
                .post(post)
                .member(member)
                .activityType(POST_LIKE)
                .build();

        newsfeedService.addNotificationToNewsfeed(post.getMember(), member, newLike.getActivityType());


        return likeRepository.save(newLike);
    }

}
