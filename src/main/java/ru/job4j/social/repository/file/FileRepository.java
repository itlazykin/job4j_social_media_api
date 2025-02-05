package ru.job4j.social.repository.file;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.social.model.File;

public interface FileRepository extends CrudRepository<File, Integer> {
}
