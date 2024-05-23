package id.ac.ui.cs.advprog.subscription.controller;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import id.ac.ui.cs.advprog.subscription.service.interfaces.SubscriptionBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription-box")
public class SubscriptionBoxController {

    private final SubscriptionBoxService subscriptionBoxService;

    @GetMapping("/")
    public ResponseEntity<List<SubscriptionBox>> getAllBoxes() {
        return subscriptionBoxService.getAllBoxes();
    }

    @GetMapping("/available")
    public ResponseEntity<List<SubscriptionBox>> getAvailableBoxes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) double price
    ) {
        return subscriptionBoxService.getAvailableBoxes();
    }

}