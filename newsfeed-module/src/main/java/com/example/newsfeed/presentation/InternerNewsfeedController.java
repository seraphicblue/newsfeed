package com.example.newsfeed.presentation;

import com.example.newsfeed.domain.NewsfeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
public class InternerNewsfeedController {
    private final NewsfeedService newsfeedService;
    @PostMapping("interner/newsfeed/follow")
    void recordFollowActivity(@RequestParam("followerId") Long followerId,
                              @RequestParam("followedId") Long followedId){
        System.out.println("도착");
        newsfeedService.recordFollowActivity(followerId,followedId);
    }

   @PostMapping("/newsfeed/comment")
    void recordCommentActivity(@RequestParam("commenterId") Long commenterId,
                               @RequestParam("postId") Long postId){
       newsfeedService.recordCommentActivity(commenterId,postId);
   }

    @PostMapping("/newsfeed/commentlike")
    void recordCommentLikeActivity(@RequestParam("likerId") Long likerId,
                                   @RequestParam("commentId") Long commentId){
        newsfeedService.recordCommentLikeActivity(likerId,commentId);
    }
    @PostMapping("/newsfeed/post")
    void recordPostActivity(@RequestParam("posterId") Long posterId){

        newsfeedService.recordPostActivity(posterId);
    }

    @PostMapping("/newsfeed/postlike")
    void recordPostLikeActivity(@RequestParam("likerId") Long likerId,
                                @RequestParam("postId") Long postId){
        newsfeedService.recordPostLikeActivity(likerId,postId);
    }

}
