package ru.job4j.social.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friends")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requesterUser;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiverUser;
}
