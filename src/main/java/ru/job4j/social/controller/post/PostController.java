package ru.job4j.social.controller.post;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.social.dto.UserPostDTO;
import ru.job4j.social.model.Post;
import ru.job4j.social.service.post.PostService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<Post> get(@PathVariable("postId")
                                    @NotNull
                                    @Min(value = 1, message = "номер поста должен быть 1 или более")
                                    Long id) {
        return postService.findPostById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPostWithoutFile(@Valid @RequestBody Post post) {
        postService.createNewPostWithoutFile(post);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Void> updatePost(@RequestBody Post post) {
        if (postService.updatePost(post) > 0) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletePost(@PathVariable("userId")
                                           @NotNull
                                           @Min(value = 1, message = "номер поста должен быть 1 и более")
                                           Integer id) {
        if (postService.deletePost(id) > 0) {
            return ResponseEntity.notFound().build();
        }
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserPostDTO>> getListPostsByUserId(@RequestParam List<Long> idUsers) {
        return ResponseEntity.ok(postService.getUserPostDto(idUsers));
    }
}
