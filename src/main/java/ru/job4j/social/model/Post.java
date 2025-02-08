package ru.job4j.social.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "Заголовок поста не должен быть пустым")
    @NonNull
    private String title;

    @NotBlank(message = "Содержание поста не должно быть пустым")
    @NonNull
    private String description;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
