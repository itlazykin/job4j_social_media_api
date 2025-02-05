package ru.job4j.social.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @NonNull
    @Column(name = "content")
    private String content;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_sender_id")
    private User senderUser;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_receiver_id")
    private User receiverUser;
}
