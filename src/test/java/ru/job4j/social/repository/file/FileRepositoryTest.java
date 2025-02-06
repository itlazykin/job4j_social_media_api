package ru.job4j.social.repository.file;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.social.model.Post;
import ru.job4j.social.model.User;
import ru.job4j.social.model.File;
import ru.job4j.social.repository.post.PostRepository;
import ru.job4j.social.repository.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileRepositoryTest {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void deleteAllFile() {
        fileRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterAll
    public void deleteAll() {
        fileRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void whenSaveFileThenFindFileById() {
        User user = new User("test1", "test1@mail.com", "test1");
        Post post = new Post("postTest1", "test1", user);
        File file = new File("test1", "testPath", post);
        userRepository.save(user);
        postRepository.save(post);
        fileRepository.save(file);
        var result = fileRepository.findById(file.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(file);
    }

    @Test
    void whenSaveFilesThenFindAll() {
        User user = new User("test1", "test1@mail.com", "test1");
        Post post = new Post("postTest1", "test1", user);
        File file1 = new File("test1", "testPath1", post);
        File file2 = new File("test2", "testPath2", post);
        File file3 = new File("test3", "testPath3", post);
        userRepository.save(user);
        postRepository.save(post);
        fileRepository.saveAll(List.of(file1, file2, file3));
        var result = (List<File>) fileRepository.findAll();
        assertThat(fileRepository.count()).isEqualTo(3);
        assertThat(result).isEqualTo(List.of(file1, file2, file3));
    }
}