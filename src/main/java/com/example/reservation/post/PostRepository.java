package com.example.reservation.post;

import com.example.reservation.postlike.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.member.memberId IN :memberId")
    List<PostLike> findPostByMId(List<Long> memberId);
}