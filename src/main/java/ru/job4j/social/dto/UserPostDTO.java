package ru.job4j.social.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserPostDTO {

    private Long userId;

    private String username;

    private List<PostDto> posts;
}
