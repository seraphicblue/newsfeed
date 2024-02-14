package com.example.reservation.activity.Infrastructure;

import com.example.reservation.activity.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    @Query("SELECT p FROM PostLike p WHERE p.member.memberId IN :memberId")
    List<PostLike> findPLikesByMId(List<Long> memberId);


}
