package ru.job4j.social.repository.subscription;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.social.model.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
}
