package com.example.reservation.activity.presentation.response;

import com.example.reservation.activity.domain.ActivityType;
import com.example.reservation.activity.domain.Follow;
import com.example.reservation.member.domain.Member;


public class FollowingResponse {
    private Long memberId;
    private Member follower;
    private Member following;
    private ActivityType activityType;

    public static FollowingResponse from(Follow follow) {

        FollowingResponse followingResponse = new FollowingResponse();
        followingResponse.memberId = follow.getMemberId();
        followingResponse.follower = follow.getFollower();
        followingResponse.following = follow.getFollowing();
        followingResponse.activityType = follow.getActivityType();

        return followingResponse;
    }
}
