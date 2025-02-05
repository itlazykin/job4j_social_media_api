package ru.job4j.social.repository.tape;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.social.model.Tape;

public interface TapeRepository extends CrudRepository<Tape, Integer> {
}
