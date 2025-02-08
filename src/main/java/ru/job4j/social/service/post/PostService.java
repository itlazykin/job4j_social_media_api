package ru.job4j.social.service.post;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.job4j.social.dto.PostDto;
import ru.job4j.social.dto.UserPostDTO;
import ru.job4j.social.mappers.PostMapper;
import ru.job4j.social.model.File;
import ru.job4j.social.model.Post;
import ru.job4j.social.model.User;
import ru.job4j.social.repository.post.PostRepository;
import ru.job4j.social.repository.user.UserRepository;
import ru.job4j.social.service.file.FileService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;

    private FileService fileService;

    private UserRepository userRepository;

    private PostMapper userPostMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Post createNewPostWithFile(Post post, File file) {
        postRepository.save(post);
        return postRepository.save(post);
    }

    @Transactional
    public Post createNewPostWithoutFile(@Valid Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Integer updatePost(Post newPost) {
        return postRepository.updateTitleAndDescriptionById(
                newPost.getTitle(),
                newPost.getDescription(),
                newPost.getId());
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

    @Transactional
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public List<UserPostDTO> getUserPostDto(List<Long> idUsers) {
        List<Post> postList = postRepository.findByUserId(idUsers);
        List<User> userList = userRepository.findAllById(idUsers);
        Map<Integer, List<PostDto>> posts = postList.stream()
                .map(userPostMapper::getDtoFromModelPost)
                .collect(Collectors.groupingBy(PostDto::getPostId));
        return userList.stream().map(user -> {
            List<PostDto> userPosts = posts.getOrDefault(user.getId(), List.of());
            return new UserPostDTO(user.getId(), user.getName(), userPosts);
        }).collect(Collectors.toList());
    }
}
