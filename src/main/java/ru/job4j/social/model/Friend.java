package ru.job4j.social.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "friends")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @NonNull
    private Boolean status;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requesterUser;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiverUser;
}
