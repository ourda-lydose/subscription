package id.ac.ui.cs.advprog.subscription.service.implementations;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import id.ac.ui.cs.advprog.subscription.repository.SubscriptionBoxRepository;
import id.ac.ui.cs.advprog.subscription.service.interfaces.SubscriptionBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SubscriptionBoxServiceImpl implements SubscriptionBoxService {
    @Autowired
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @Override
    @Async
    public CompletableFuture<SubscriptionBox> saveBox(SubscriptionBox box) {
        return CompletableFuture.completedFuture(subscriptionBoxRepository.save(box));
    }

    @Override
    @Async
    public CompletableFuture<List<SubscriptionBox>> getAllBoxes() {
        return CompletableFuture.completedFuture(subscriptionBoxRepository.findAll());
    }

    @Override
    @Async
    public CompletableFuture<Optional<SubscriptionBox>> getBoxById(String id) {
        return CompletableFuture.completedFuture(subscriptionBoxRepository.findById(id));
    }

    @Override
    @Async
    public CompletableFuture<List<SubscriptionBox>> getBoxByName(String name) {
        return CompletableFuture.completedFuture(subscriptionBoxRepository.findByName(name));
    }

//    @Override
//    @Async
//    public CompletableFuture<Optional<SubscriptionBox>> getBoxByPrice(int price) {
//        return CompletableFuture.completedFuture(subscriptionBoxRepository.findByPrice(price));
//    }

    @Override
    @Async
    public CompletableFuture<Void> deleteBox(String id) {
        try {
            Optional<SubscriptionBox> optionalBook = subscriptionBoxRepository.findById(id);
            if (optionalBook.isPresent()) {
                subscriptionBoxRepository.delete(optionalBook.get());
            } else {
                throw new NoSuchElementException("Box with id " + id + " not found");
            }
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }
}