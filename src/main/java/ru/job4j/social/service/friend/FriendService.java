package ru.job4j.social.service.friend;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.social.model.Friend;
import ru.job4j.social.model.Subscription;
import ru.job4j.social.repository.friend.FriendRepository;
import ru.job4j.social.service.subscription.SubscriptionService;

@Service
@AllArgsConstructor
public class FriendService {

    private FriendRepository friendRepository;

    private SubscriptionService subscriptionService;

    @Transactional
    public void createFriend(Friend friend, Subscription subscription) {
        friendRepository.save(friend);
        subscriptionService.saveSubscription(subscription);
    }

    @Transactional
    public void deleteFriendById(Integer id) {
        friendRepository.deleteById(id);
        subscriptionService.deleteSubscriptionById(id);
    }
}
