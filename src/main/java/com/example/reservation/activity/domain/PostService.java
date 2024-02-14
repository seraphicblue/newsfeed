package com.example.reservation.activity.domain;

import com.example.reservation.activity.Infrastructure.PostRepository;
import com.example.reservation.member.domain.Member;
import com.example.reservation.member.memberrepository.MemberRepository;
import com.example.reservation.newsfeed.domain.NewsfeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.reservation.activity.domain.ActivityType.POST;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final NewsfeedService newsfeedService;

    public Post createPost(Long memberId, String content) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을수 없습니다"));

        Post newPost = Post.builder()
                .content(content)
                .member(member)
                .activityType(POST)
                .build();


        newsfeedService.addNotificationToNewsfeed(newPost.getMember(),member, newPost.getActivityType());
        return postRepository.save(newPost);
    }


}


