package com.example.reservation.post;

import com.example.reservation.comment.Comment;
import com.example.reservation.comment.CommentService;
import com.example.reservation.postlike.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final PostLikeService likeService;

    public PostController(PostService postService, CommentService commentService, PostLikeService likeService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
    }


   /* @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }*/


    /*//게시물 작성
    @PostMapping("/post/create")
    public ResponseEntity<Post> createPost(@RequestHeader("login-user") Long memberId, @RequestBody String content) {
        Post newPost = postService.createPost(memberId, content);
        return ResponseEntity.ok(newPost);
    }

    //댓글 작성
    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> createComment(@RequestHeader("login-user") Long memberId, @PathVariable Long postId, @RequestBody String content) {
        Comment newComment = commentService.createComment(memberId, postId, content);
        return ResponseEntity.ok(newComment);
    }

    //포스트 좋아요 작성
    @PostMapping("/{postId}/postlikes")
    public ResponseEntity<?> likePost(@RequestHeader("login-user") Long memberId, @PathVariable Long postId) {
        likeService.likePost(memberId, postId);
        return ResponseEntity.ok().build();
    }

    //댓글 좋아요 작성
    @PostMapping("/{commentId}/commentlikes")
    public ResponseEntity<?> likeComment(@RequestHeader("login-user") Long memberId, @PathVariable Long commentId) {
        likeService.likePost(memberId, commentId);
        return ResponseEntity.ok().build();
    }
*/
}
