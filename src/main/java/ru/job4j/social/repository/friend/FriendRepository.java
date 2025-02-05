package ru.job4j.social.repository.friend;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.social.model.Friend;

public interface FriendRepository extends CrudRepository<Friend, Integer> {
}
