package com.example.reservation.newsfeed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, String> {
    @Query("SELECT n FROM Newsfeed n WHERE n.receiver.memberId = :receiverId ORDER BY n.createdAt DESC")
    List<Newsfeed> findByReceiverId(@Param("receiverId") Long receiverId);
}
