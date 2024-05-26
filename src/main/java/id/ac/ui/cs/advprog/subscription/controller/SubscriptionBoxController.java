package id.ac.ui.cs.advprog.subscription.controller;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import id.ac.ui.cs.advprog.subscription.service.interfaces.SubscriptionBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription-box")
public class SubscriptionBoxController {
    @Autowired
    private SubscriptionBoxService subscriptionBoxService;

    @PostMapping
    public CompletableFuture<ResponseEntity<SubscriptionBox>> saveBox(@RequestBody SubscriptionBox box) {
        return subscriptionBoxService.saveBox(box)
                .thenApply(savedBox -> new ResponseEntity<>(savedBox, HttpStatus.CREATED));
    }

    @GetMapping("/byid/{id}")
    public CompletableFuture<ResponseEntity<SubscriptionBox>> getBoxById(@PathVariable("id") String id) {
        return subscriptionBoxService.getBoxById(id)
                .thenApply(box -> box.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> getAllBoxes() {
        return subscriptionBoxService.getAllBoxes()
                .thenApply(box -> new ResponseEntity<>(box, HttpStatus.OK));
    }

    @GetMapping("/byname/{name}")
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> getBoxByName(@PathVariable("name") String name) {
        return subscriptionBoxService.getBoxByName(name)
                .thenApply(box -> new ResponseEntity<>(box, HttpStatus.OK));
    }

//    @GetMapping("/byprice/{price}")
//    public CompletableFuture<ResponseEntity<SubscriptionBox>> getBoxByPrice(@PathVariable("price") int price) {
//        return subscriptionBoxService.getBoxById(price)
//                .thenApply(box -> box.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));
//    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<String>> deleteBox(@PathVariable("id") String idBox) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                subscriptionBoxService.deleteBox(idBox);
                return new ResponseEntity<>("Box with id " + idBox + " has been deleted", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>("Box with id " + idBox + " not found", HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity<>("Failed to delete Box with id " + idBox, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        });
    }

//    @GetMapping("/available")
//    public ResponseEntity<List<SubscriptionBox>> getAvailableBoxes(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String description,
//            @RequestParam(required = false) double price
//    ) {
//        return subscriptionBoxService.getAvailableBoxes();
//    }

}