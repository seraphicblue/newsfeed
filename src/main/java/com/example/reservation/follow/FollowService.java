package com.example.reservation.follow;

import java.util.List;

public interface FollowService {
    void follow(Long follower, Long following);

    List<Follow> findFollowing(Long memberId);

    List<Follow> findFollowers(Long memberId);

    List<Long> getFollowingUserIds(Long memberId);
}