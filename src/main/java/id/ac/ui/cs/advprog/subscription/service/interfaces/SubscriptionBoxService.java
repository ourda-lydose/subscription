package id.ac.ui.cs.advprog.subscription.service.interfaces;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionBoxService {
    public ResponseEntity<List<SubscriptionBox>> getAllBoxes();

    public ResponseEntity<List<SubscriptionBox>> getAvailableBoxes();

}