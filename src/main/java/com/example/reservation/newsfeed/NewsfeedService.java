package com.example.reservation.newsfeed;

import com.example.reservation.activity.ActivityType;
import com.example.reservation.member.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;

    public NewsfeedService(NewsfeedRepository newsfeedRepository) {
        this.newsfeedRepository = newsfeedRepository;

    }

    public void addNotificationToNewsfeed(Member receiver, Member sender, ActivityType activityType) {

        Newsfeed newsfeed = Newsfeed.builder()
                .receiver(receiver)
                .sender(sender)
                .activityType(activityType)
                .build();

        newsfeedRepository.save(newsfeed);
    }

    public List<NewsfeedResponse> getNewsfeed(Long receiverId) {
        List<Newsfeed> newsfeeds = newsfeedRepository.findByReceiverId(receiverId);
        List<NewsfeedResponse> responses = new ArrayList<>();

        for(Newsfeed newsfeed : newsfeeds) {
            NewsfeedResponse response = NewsfeedResponse.from(newsfeed);
            responses.add(response);
        }

        return responses; // Response 객체를 생성하여 리스트에 추가
    }
}
