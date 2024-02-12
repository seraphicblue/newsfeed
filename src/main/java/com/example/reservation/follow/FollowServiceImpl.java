package com.example.reservation.follow;

import com.example.reservation.member.Member;
import com.example.reservation.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.reservation.activity.ActivityType.FOLLOW;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;


    public FollowServiceImpl(FollowRepository followRepository, MemberRepository memberRepository) {
        this.followRepository = followRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void follow(Long followerId, Long followingId) {
        Member follower = memberRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("팔로워가 없습니다."));
        Member following = memberRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("팔로잉이 없습니다."));

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .activityType(FOLLOW)
                .build();

        followRepository.save(follow);
    }

    @Override
    public List<Follow> findFollowing(Long memberId) {

        return followRepository.findByFollowingMemberId(memberId);
    }

    @Override
    public List<Follow> findFollowers(Long memberId) {

        return followRepository.findByFollowerMemberId(memberId);
    }

    @Override
    public List<Long> getFollowingUserIds(Long memberId) {
        List<Follow> follows = followRepository.findByFollowingMemberId(memberId);
        return follows.stream().map(follow -> follow.getFollowing().getMemberId()).collect(Collectors.toList());
    }


}
