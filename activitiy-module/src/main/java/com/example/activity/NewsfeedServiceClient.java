package com.example.activity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "newsfeed-service", url = "localhost:8083")
public interface NewsfeedServiceClient {
    @PostMapping("/newsfeed/follow")
    void recordFollowActivity(@RequestParam("followerId") Long followerId,
                              @RequestParam("followedId") Long followedId);

    @PostMapping("/newsfeed/comment")
    void recordCommentActivity(@RequestParam("commenterId") Long commenterId,
                               @RequestParam("postId") Long postId);

    @PostMapping("/newsfeed/commentlike")
    void recordCommentLikeActivity(@RequestParam("likerId") Long likerId,
                                   @RequestParam("commentId") Long commentId);

    @PostMapping("/newsfeed/post")
    void recordPostActivity(@RequestParam("posterId") Long posterId);

    @PostMapping("/newsfeed/postlike")
    void recordPostLikeActivity(@RequestParam("likerId") Long likerId,
                                @RequestParam("postId") Long postId);
}
