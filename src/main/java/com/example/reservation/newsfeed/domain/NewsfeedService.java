package com.example.reservation.newsfeed.domain;

import com.example.reservation.activity.domain.ActivityType;
import com.example.reservation.member.domain.Member;
import com.example.reservation.newsfeed.infrastructure.NewsfeedRepository;
import com.example.reservation.newsfeed.presentation.NewsfeedResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;

    public NewsfeedService(NewsfeedRepository newsfeedRepository) {
        this.newsfeedRepository = newsfeedRepository;

    }
    //activity 활동시 뉴스피드를 생성할 메서드
    public void addNotificationToNewsfeed(Member receiver, Member sender, ActivityType activityType) {

        Newsfeed newsfeed = Newsfeed.builder()
                .receiver(receiver)
                .sender(sender)
                .activityType(activityType)
                .build();

        newsfeedRepository.save(newsfeed);
    }
    
    //응답자가 받은 여러 뉴스 피드를 리스폰스로 담을 메서드
    public List<NewsfeedResponse> getNewsfeed(Long receiverId) {
        List<Newsfeed> newsfeeds = newsfeedRepository.findByReceiverId(receiverId);
        List<NewsfeedResponse> responses = new ArrayList<>();

        for(Newsfeed newsfeed : newsfeeds) {
            NewsfeedResponse response = NewsfeedResponse.from(newsfeed);
            responses.add(response);
        }

        return responses;
    }
}
