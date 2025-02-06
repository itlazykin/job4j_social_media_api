package ru.job4j.social.service.subscription;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.social.model.Subscription;
import ru.job4j.social.repository.subscription.SubscriptionRepository;

@Service
@AllArgsConstructor
public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    @Transactional
    public void deleteSubscriptionById(Integer id) {
        subscriptionRepository.deleteById(id);
    }
}
