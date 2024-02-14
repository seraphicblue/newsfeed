package com.example.reservation.newsfeed.domain;

import com.example.reservation.activity.domain.ActivityType;
import com.example.reservation.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "newsfeed")
@EntityListeners(AuditingEntityListener.class) // 엔티티 리스너 등록
public class Newsfeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newsfeed_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Member sender;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Newsfeed(Member receiver, Member sender, ActivityType activityType) {
        this.receiver = receiver;
        this.sender = sender;
        this.activityType = activityType;

    }
}