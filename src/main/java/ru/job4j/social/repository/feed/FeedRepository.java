package ru.job4j.social.repository.feed;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.social.model.Feed;
import ru.job4j.social.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Integer> {

    List<Feed> findByUser(User user);

    List<Feed> findByCreatedPostGreaterThanEqualAndCreatedPostLessThanEqual(LocalDateTime startTime, LocalDateTime endTime);

    Page<Feed> findByOrderByCreatedPostDesc(Pageable pageable);
}
