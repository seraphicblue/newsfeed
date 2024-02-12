package com.example.reservation.newsfeed;

import com.example.reservation.activity.ActivityType;
import java.time.LocalDateTime;

public class NewsfeedResponse {
    private Long id;
    private Long receiverId;
    private Long senderId;
    private ActivityType activityType;
    private LocalDateTime createdAt;

    public static NewsfeedResponse from(Newsfeed newsfeed){

        NewsfeedResponse newsfeedResponse = new NewsfeedResponse();
        newsfeedResponse.id = newsfeed.getId();
        newsfeedResponse.receiverId = newsfeed.getReceiver().getMemberId();
        newsfeedResponse.senderId = newsfeed.getSender().getMemberId();
        newsfeedResponse.activityType = newsfeed.getActivityType();
        newsfeedResponse.createdAt = newsfeed.getCreatedAt();

        return newsfeedResponse;
    }
}
