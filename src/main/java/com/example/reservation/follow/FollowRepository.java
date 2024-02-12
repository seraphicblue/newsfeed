package com.example.reservation.follow;

import com.example.reservation.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT f FROM Follow f WHERE f.follower.memberId = :memberId")
    List<Follow> findByFollowerMemberId(Long memberId);

    @Query("SELECT f FROM Follow f WHERE f.following.memberId = :memberId")
    List<Follow> findByFollowingMemberId(Long memberId);
}



