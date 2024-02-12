package com.example.reservation.follow;

import com.example.reservation.post.PostService;
import com.example.reservation.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/activity/follow")
public class FollowController {

    private final FollowService followService;


    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    //팔로우 하기
    @PostMapping
    public ResponseEntity<Void> follow(
            @RequestBody Map<String, Long> requestBody,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        if (userDetails == null) {
            // 사용자가 인증되지 않았을 경우의 처리 로직
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long followingId = requestBody.get("followingId");
        if (followingId == null) {
            return ResponseEntity.badRequest().build(); // 요청 본문에 followingId가 없는 경우
        }
        followService.follow(userDetails.getMemberId(), followingId);
        return ResponseEntity.ok().build();
    }


    // 유저의 팔로잉 목록을 조회
    @GetMapping("/{memberId}/following")
    public ResponseEntity<List<Follow>> getFollowing(@PathVariable Long memberId) {
        List<Follow> following = followService.findFollowing(memberId);
        return ResponseEntity.ok(following);
    }

    // 유저의 팔로우 목록을 조회
    @GetMapping("/{memberId}/followers")
    public ResponseEntity<List<Follow>> getFollowers(@PathVariable Long memberId) {
        List<Follow> followers = followService.findFollowers(memberId);
        return ResponseEntity.ok(followers);
    }


}
