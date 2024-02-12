package com.example.reservation.commentlike;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    @Query("SELECT c FROM CommentLike c WHERE c.member.memberId IN :memberIds")
    List<CommentLike> findCLikesByMIds(List<Long> memberIds);
}

