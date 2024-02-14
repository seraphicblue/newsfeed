CREATE TABLE member (
                        member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        name VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        profile VARCHAR(255),
                        greeting VARCHAR(255)
);
CREATE TABLE follow (
                        follow_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        follower BIGINT,
                        following BIGINT,
                        activity_type VARCHAR(255) NOT NULL,
                        FOREIGN KEY (follower) REFERENCES member(member_id),
                        FOREIGN KEY (following) REFERENCES member(member_id)
);
CREATE TABLE post (
                      post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      member_id BIGINT NOT NULL,
                      content TEXT NOT NULL,
                      created_at DATETIME NOT NULL,
                      updated_at DATETIME NOT NULL,
                      activity_type VARCHAR(255) NOT NULL,
                      FOREIGN KEY (member_id) REFERENCES member(member_id)
);
CREATE TABLE postlike (
                          post_like_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          post_id BIGINT NOT NULL,
                          member_id BIGINT NOT NULL,
                          activity_type VARCHAR(255) NOT NULL,
                          FOREIGN KEY (post_id) REFERENCES post(post_id),
                          FOREIGN KEY (member_id) REFERENCES member(member_id)
);
CREATE TABLE comment (
                         comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         content TEXT NOT NULL,
                         post_id BIGINT NOT NULL,
                         member_id BIGINT NOT NULL,
                         activity_type VARCHAR(255) NOT NULL,
                         FOREIGN KEY (post_id) REFERENCES post(post_id),
                         FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE commentlike (
                             comment_like_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             comment_id BIGINT NOT NULL,
                             member_id BIGINT NOT NULL,
                             activity_type VARCHAR(255) NOT NULL,
                             FOREIGN KEY (comment_id) REFERENCES comment(comment_id),
                             FOREIGN KEY (member_id) REFERENCES member(member_id)
);
CREATE TABLE activity (
                          activity_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          member_id BIGINT,
                          activity_type VARCHAR(255),
                          post_id BIGINT,
                          follow_id BIGINT,
                          comment_id BIGINT,
                          post_like_id BIGINT,
                          comment_like_id BIGINT,
                          created_at DATETIME,
                          FOREIGN KEY (member_id) REFERENCES member(member_id),
                          FOREIGN KEY (post_id) REFERENCES post(post_id),
                          FOREIGN KEY (follow_id) REFERENCES follow(follow_id),
                          FOREIGN KEY (comment_id) REFERENCES comment(comment_id),
                          FOREIGN KEY (post_like_id) REFERENCES postlike(post_like_id),
                          FOREIGN KEY (comment_like_id) REFERENCES commentlike(comment_like_id)
);
CREATE TABLE newsfeed (
                          newsfeed_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          receiver_id BIGINT,
                          sender_id BIGINT,
                          activity_type VARCHAR(255) NOT NULL,
                          created_at DATETIME NOT NULL,
                          FOREIGN KEY (receiver_id) REFERENCES member(member_id),
                          FOREIGN KEY (sender_id) REFERENCES member(member_id)
);
