package com.example.reservation.activity.presentation;

import com.example.reservation.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowCreate {
    private Member follower;
    private Member following;

    @Builder
    public FollowCreate(final Member follower, final Member following) {
        this.follower = follower;
        this.following = following;
    }
}
