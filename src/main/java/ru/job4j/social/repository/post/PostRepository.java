package ru.job4j.social.repository.post;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.social.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
