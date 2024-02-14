package com.example.reservation.activity.presentation.response;

import com.example.reservation.activity.domain.ActivityType;
import com.example.reservation.activity.domain.Follow;
import com.example.reservation.member.domain.Member;

public class FollowerResponse {
    private Long memberId;
    private Member follower;
    private Member following;
    private ActivityType activityType;

    public static FollowerResponse from(Follow follow) {

        FollowerResponse followerResponse = new FollowerResponse();
        followerResponse.memberId = follow.getMemberId();
        followerResponse.follower = follow.getFollower();
        followerResponse.following = follow.getFollowing();
        followerResponse.activityType = follow.getActivityType();

        return followerResponse;
    }
}
