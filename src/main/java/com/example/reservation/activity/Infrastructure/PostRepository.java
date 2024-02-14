package com.example.reservation.activity.Infrastructure;

import com.example.reservation.activity.domain.Post;
import com.example.reservation.activity.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.member.memberId IN :memberId")
    List<PostLike> findPostByMId(List<Long> memberId);
}