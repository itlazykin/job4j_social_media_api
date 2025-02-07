package ru.job4j.social.repository.feed;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.social.model.Post;
import ru.job4j.social.model.Feed;
import ru.job4j.social.model.User;
import ru.job4j.social.repository.post.PostRepository;
import ru.job4j.social.repository.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class FeedRepositoryTest {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void deleteAllTape() {
        feedRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterAll
    public void deleteAll() {
        feedRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void whenSavePostsInTapeThenFindByIdAndFindAll() {
        User user = new User("test1", "test1@mail.com", "test1");
        Post post1 = new Post("postTest1", "test1", user);
        Post post2 = new Post("postTest2", "test2", user);
        Post post3 = new Post("postTest3", "test3", user);
        Feed feed1 = new Feed(user, post1);
        Feed feed2 = new Feed(user, post2);
        Feed feed3 = new Feed(user, post3);
        userRepository.save(user);
        postRepository.saveAll(List.of(post1, post2, post3));
        feedRepository.saveAll(List.of(feed1, feed2, feed3));
        var result = feedRepository.findById(feed2.getId());
        var resultList = feedRepository.findAll();
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(feed2);
        assertThat(feedRepository.count()).isEqualTo(3);
        assertThat(resultList).isEqualTo(List.of(feed1, feed2, feed3));
    }
}