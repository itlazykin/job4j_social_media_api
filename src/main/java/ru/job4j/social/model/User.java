package ru.job4j.social.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Schema(description = "User Model Information")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "имя пользователя не должно быть пустым")
    @Length(min = 2, message = "имя пользователя должно содержать не менее 2 символов")
    @NonNull
    private String name;

    @Email
    @NonNull
    private String email;

    @NonNull
    private String password;
}
