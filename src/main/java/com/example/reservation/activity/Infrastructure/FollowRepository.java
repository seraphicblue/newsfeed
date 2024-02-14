package com.example.reservation.activity.Infrastructure;

import com.example.reservation.activity.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT f FROM Follow f WHERE f.follower.memberId = :memberId")
    List<Follow> findFollowingById(Long memberId);

    @Query("SELECT f FROM Follow f WHERE f.following.memberId = :memberId")
    List<Follow> findFollowerById(Long memberId);

}



