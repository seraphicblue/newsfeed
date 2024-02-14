package com.example.reservation.activity.presentation;

import com.example.reservation.activity.domain.Comment;
import com.example.reservation.activity.domain.CommentService;
import com.example.reservation.activity.domain.CommentLikeService;
import com.example.reservation.activity.domain.Post;
import com.example.reservation.activity.domain.PostService;
import com.example.reservation.activity.domain.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    private final PostService postService;
    private final CommentService commentService;
    private final PostLikeService postLikeService;
    private final CommentLikeService commentLikeService;


    // 게시물 작성
    @PostMapping("/posts/{memberId}")
    public ResponseEntity<Post> createPost(@PathVariable(name = "memberId") Long memberId,
                                           @RequestBody String content) {
        Post newPost = postService.createPost(memberId, content);
        return ResponseEntity.ok(newPost);
    }

    // 댓글 작성
    @PostMapping("/posts/{postId}/comments/{memberId}")
    public ResponseEntity<Comment> createComment(@PathVariable(name = "memberId") Long memberId,
                                                 @PathVariable(name = "postId") Long postId,
                                                 @RequestBody String content) {
        Comment newComment = commentService.createComment(memberId, postId, content);
        return ResponseEntity.ok(newComment);
    }

    // 포스트 좋아요
    @PostMapping("/posts/{postId}/likes/{memberId}")
    public ResponseEntity<?> likePost(@PathVariable(name = "memberId") Long memberId,
                                      @PathVariable(name = "postId") Long postId) {
        postLikeService.likePost(memberId, postId);
        return ResponseEntity.ok().build();
    }

    // 댓글 좋아요
    @PostMapping("/comments/{commentId}/likes/{memberId}")
    public ResponseEntity<?> likeComment(@PathVariable(name = "memberId") Long memberId,
                                         @PathVariable(name = "commentId") Long commentId) {
        commentLikeService.likeComment(memberId, commentId);
        return ResponseEntity.ok().build();
    }
}
