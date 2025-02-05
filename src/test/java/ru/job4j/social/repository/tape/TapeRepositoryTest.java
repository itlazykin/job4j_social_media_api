package ru.job4j.social.repository.tape;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.social.model.Post;
import ru.job4j.social.model.Tape;
import ru.job4j.social.model.User;
import ru.job4j.social.repository.post.PostRepository;
import ru.job4j.social.repository.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class TapeRepositoryTest {

    @Autowired
    private TapeRepository tapeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void deleteAllTape() {
        tapeRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterAll
    public void deleteAll() {
        tapeRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void whenSavePostsInTapeThenFindByIdAndFindAll() {
        User user = new User("test1", "test1@mail.com", "test1");
        Post post1 = new Post("postTest1", "test1", user);
        Post post2 = new Post("postTest2", "test2", user);
        Post post3 = new Post("postTest3", "test3", user);
        Tape tape1 = new Tape(user, post1);
        Tape tape2 = new Tape(user, post2);
        Tape tape3 = new Tape(user, post3);
        userRepository.save(user);
        postRepository.saveAll(List.of(post1, post2, post3));
        tapeRepository.saveAll(List.of(tape1, tape2, tape3));
        var result = tapeRepository.findById(tape2.getId());
        var resultList = tapeRepository.findAll();
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(tape2);
        assertThat(tapeRepository.count()).isEqualTo(3);
        assertThat(resultList).isEqualTo(List.of(tape1, tape2, tape3));
    }
}