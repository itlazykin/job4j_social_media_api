package ru.job4j.social.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "tapes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @NonNull
    @Column(name = "created_post")
    private LocalDateTime createdPost = LocalDateTime.now().withNano(0);
}
