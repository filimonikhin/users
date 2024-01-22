package skillbox.com.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skillbox.com.users.dto.SubscriptionDto;
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
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        return new ResponseEntity<>(subscriptionService.getAllSubscriptions(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<SubscriptionDto> createSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        return new ResponseEntity<>(subscriptionService.createSubscription(subscriptionDto), HttpStatus.OK);
    }

    @DeleteMapping("/{subscriptionId}")
    ResponseEntity<Boolean> deleteSubscription(@PathVariable Integer subscriptionId) {
        boolean deleted = subscriptionService.deleteSubscription(subscriptionId);

        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
