package ru.job4j.social.repository.friend;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.social.model.Friend;
import ru.job4j.social.model.User;
import ru.job4j.social.repository.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FriendRepositoryTest {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void deleteAllFriends() {
        friendRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterAll
    public void deleteAll() {
        friendRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void whenSaveStatusFriendThenFindFriendById() {
        User user1 = new User("test1", "test1@mail.com", "test1");
        User user2 = new User("test2", "test2@mail.com", "test2");
        Friend friend = new Friend(true, user1, user2);
        userRepository.save(user1);
        userRepository.save(user2);
        friendRepository.save(friend);
        var result = friendRepository.findById(friend.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(friend);
    }

    @Test
    void whenSaveStatusFriendsThenFindAllFriends() {
        User user1 = new User("test1", "test1@mail.com", "test1");
        User user2 = new User("test2", "test2@mail.com", "test2");
        User user3 = new User("test3", "test3@mail.com", "test3");
        Friend friend1 = new Friend(true, user1, user2);
        Friend friend2 = new Friend(false, user2, user3);
        userRepository.saveAll(List.of(user1, user2, user3));
        friendRepository.saveAll(List.of(friend1, friend2));
        var result = (List<Friend>) friendRepository.findAll();
        assertThat(friendRepository.count()).isEqualTo(2);
        assertThat(result).isEqualTo(List.of(friend1, friend2));
    }
}