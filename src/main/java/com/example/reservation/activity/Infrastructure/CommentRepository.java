package com.example.reservation.activity.Infrastructure;

import com.example.reservation.activity.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.member.memberId IN :memberId")
    List<Comment> findByUserIdsIn(@Param("memberId") List<Long> memberId);

}
