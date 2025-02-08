package ru.job4j.social.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PostDto {

    private int postId;

    private String title;
}
