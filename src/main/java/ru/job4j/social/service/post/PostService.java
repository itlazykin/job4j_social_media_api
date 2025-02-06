package ru.job4j.social.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.social.model.File;
import ru.job4j.social.model.Post;
import ru.job4j.social.repository.post.PostRepository;
import ru.job4j.social.service.file.FileService;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;

    private FileService fileService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveNewPost(Post post, File file) {
        postRepository.save(post);
        fileService.createFile(file);
    }

    @Transactional
    public Integer updatePost(Post newPost) {
        return postRepository.updateTitleAndDescriptionById(newPost.getTitle(), newPost.getDescription(), newPost.getId());
    }

    @Transactional
    public Integer deletePost(Integer id) {
        fileService.deleteFileById(id);
        return postRepository.deletePostById(id);
    }

    @Transactional
    public void deleteAllPost() {
        postRepository.deleteAll();
        fileService.deleteAllFile();
    }
}
