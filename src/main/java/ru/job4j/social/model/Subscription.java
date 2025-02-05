package ru.job4j.social.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "subscriptions")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_subscriber_id")
    private User subscriberUser;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_target_id")
    private User targetUser;
}
