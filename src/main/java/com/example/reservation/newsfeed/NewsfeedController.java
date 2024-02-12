package com.example.reservation.newsfeed;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newsfeed")
public class NewsfeedController {

    private final NewsfeedService newsfeedService;

    public NewsfeedController(NewsfeedService newsfeedService) {
        this.newsfeedService = newsfeedService;
    }

    // 뉴스피드 불러오기
    @GetMapping("/{memberId}")
    public ResponseEntity<List<NewsfeedResponse>> getNewsfeed(@PathVariable(name = "memberId") Long receiverId) {
        List<NewsfeedResponse> newsfeeds = newsfeedService.getNewsfeed(receiverId);
        return ResponseEntity.ok(newsfeeds);
    }
}
