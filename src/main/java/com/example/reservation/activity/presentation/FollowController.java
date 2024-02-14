package com.example.reservation.activity.presentation;

import com.example.reservation.activity.domain.FollowService;
import com.example.reservation.activity.presentation.response.FollowerResponse;
import com.example.reservation.activity.presentation.response.FollowingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/activity/follow")
public class FollowController {

    private final FollowService followService;

    //팔로우 하기
    @PostMapping("/{memberId}")
    public ResponseEntity<Void> follow(
            @RequestBody FollowCreate followCreate
    ) {

        followService.follow(followCreate);
        return ResponseEntity.ok().build();
    }

    // 유저의 팔로잉 목록을 조회
    @GetMapping("/{memberId}/following")
    public ResponseEntity<List<FollowingResponse>> getFollowing(@PathVariable Long memberId) {
        List<FollowingResponse> followingList = followService.findFollowing(memberId);
        return ResponseEntity.ok(followingList);
    }

    // 유저의 팔로우 목록을 조회
    @GetMapping("/{memberId}/followers")
    public ResponseEntity<List<FollowerResponse>> getFollowers(@PathVariable Long memberId) {
        List<FollowerResponse> followersList = followService.findFollowers(memberId);
        return ResponseEntity.ok(followersList);
    }


}
