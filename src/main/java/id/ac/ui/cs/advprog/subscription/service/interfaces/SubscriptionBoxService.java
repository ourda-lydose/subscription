package id.ac.ui.cs.advprog.subscription.service.interfaces;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public interface SubscriptionBoxService {
    CompletableFuture<SubscriptionBox> saveBox(SubscriptionBox subscriptionBox);

    public  CompletableFuture<List<SubscriptionBox>> getAllBoxes();

    public  CompletableFuture<Optional<SubscriptionBox>> getBoxById(String id);

    public CompletableFuture<List<SubscriptionBox>> getBoxByName(String name);

//    public  CompletableFuture<Optional<SubscriptionBox>> getBoxByPrice(int price);

    CompletableFuture<Void> deleteBox(String id);
}