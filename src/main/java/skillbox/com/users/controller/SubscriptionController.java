package skillbox.com.users.controller;

import org.springframework.web.bind.annotation.*;
import skillbox.com.users.entity.SubscriptionEntity;
import skillbox.com.users.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping(value = "/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public List<SubscriptionEntity> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @PostMapping
    String createSubscription(@RequestBody SubscriptionEntity subscriptionEntity) {
        return subscriptionService.createSubscription(subscriptionEntity);
    }

    @DeleteMapping("/{subscriptionId}")
    String deleteUser(@PathVariable Integer subscriptionId) {
        return subscriptionService.deleteSubscription(subscriptionId);
    }
}
