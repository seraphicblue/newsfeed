package com.example.reservation.activity.domain;

import com.example.reservation.activity.Infrastructure.FollowRepository;
import com.example.reservation.activity.presentation.response.FollowerResponse;
import com.example.reservation.activity.presentation.response.FollowingResponse;
import com.example.reservation.activity.presentation.FollowCreate;
import com.example.reservation.member.memberrepository.MemberRepository;
import com.example.reservation.member.domain.Member;
import com.example.reservation.newsfeed.domain.NewsfeedService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static com.example.reservation.activity.domain.ActivityType.FOLLOW;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;
    private final NewsfeedService newsfeedservice;

    @Transactional
    public void follow(FollowCreate followCreate) {

        Member follower = memberRepository.findById(followCreate.getFollower().getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("사용자의 아이디가 없습니다."));
        Member following = memberRepository.findById(followCreate.getFollowing().getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("팔로우할 사용자의 아이디가 존재하지 않습니다."));

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .activityType(FOLLOW)
                .build();

        //뉴스피드 서비스 요청
        newsfeedservice.addNotificationToNewsfeed(followCreate.getFollower(), followCreate.getFollowing(),
                follow.getActivityType());

        followRepository.save(follow);
    }

    public List<FollowingResponse> findFollowing(Long memberId) {

        List<Follow> followingList = followRepository.findFollowingById(memberId);
        List<FollowingResponse> followingResponses = new ArrayList<>();

        for (Follow follow : followingList) {
            FollowingResponse response = FollowingResponse.from(follow);
            followingResponses.add(response);
        }

        return followingResponses;
    }


    public List<FollowerResponse> findFollowers(Long memberId) {

        List<Follow> followerList = followRepository.findFollowerById(memberId);
        List<FollowerResponse> followerResponse = new ArrayList<>();

        for (Follow follow : followerList) {
            FollowerResponse response = FollowerResponse.from(follow);
            followerResponse.add(response);
        }

        return followerResponse;

    }


}