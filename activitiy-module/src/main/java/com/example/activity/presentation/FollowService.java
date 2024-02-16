package com.example.activity.presentation;

import com.example.activity.MemberClient;
import com.example.activity.NewsfeedServiceClient;
import com.example.activity.Infrastructure.FollowRepository;
import com.example.activity.domain.Follow;
import com.example.activity.presentation.response.FollowerResponse;
import com.example.activity.presentation.response.FollowingResponse;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static com.example.activity.domain.ActivityType.FOLLOW;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    private final NewsfeedServiceClient newsfeedServiceClient;
    private final MemberClient memberClient;
    public FollowService(FollowRepository followRepository, NewsfeedServiceClient newsfeedServiceClient, MemberClient memberClient) {
        this.followRepository = followRepository;
        this.newsfeedServiceClient = newsfeedServiceClient;
        this.memberClient = memberClient;
    }

    @Transactional
    public void follow(FollowCreate followCreate) {
        /*MemberDto followerDto = null;
        MemberDto followingDto = null;

        try {
            // 팔로워 정보 조회
            followerDto = memberClient.getMemberById(followCreate.getFollower());
        } catch (Exception ex) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팔로워 사용자의 아이디가 없습니다: " + followCreate.getFollower(), ex);
        }

        try {
            // 팔로잉 정보 조회
            followingDto = memberClient.getMemberById(followCreate.getFollowing());
        } catch (Exception ex) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "팔로우할 사용자의 아이디가 존재하지 않습니다: " + followCreate.getFollowing(), ex);
        }
*/


        Follow follow = Follow.builder()
                .follower(followCreate.getFollower()) // 팔로워 ID
                .following(followCreate.getFollowing()) // 팔로잉 ID
                .activityType(FOLLOW)
                .build();

        followRepository.save(follow);

        newsfeedServiceClient.recordFollowActivity(followCreate.getFollower(), followCreate.getFollowing());



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


