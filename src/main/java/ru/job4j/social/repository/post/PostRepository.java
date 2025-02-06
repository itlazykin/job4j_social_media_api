package ru.job4j.social.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.social.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = """
            UPDATE posts
            SET title = :title1, description = :description2
            WHERE id = :id3
            """, nativeQuery = true
    )
    int updateTitleAndDescriptionById(
            @Param("title1") String title,
            @Param("description2") String description,
            @Param("id3") Long id
    );

    @Modifying(clearAutomatically = true)
    @Query(value = """
            DELETE FROM posts
            WHERE id = :id
            """, nativeQuery = true
    )
    int deletePostById(@Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query(
            value = """
            DELETE FROM files
            WHERE post_id = :id
            """, nativeQuery = true
    )
    int deleteFileById(@Param("id") Integer id);

    @Query(value = """
            SELECT p FROM posts AS p
            JOIN users AS u ON p.user_id = u.id
            WHERE p.user_id IN :idUsers
            ORDER BY p.id ASC
            """, nativeQuery = true
    )
    List<Post> findByUserId(@Param("idUsers") List<Long> idUsers);
}
