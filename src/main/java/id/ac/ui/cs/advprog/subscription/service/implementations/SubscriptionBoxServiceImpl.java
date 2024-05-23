package id.ac.ui.cs.advprog.subscription.service.implementations;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import id.ac.ui.cs.advprog.subscription.service.interfaces.SubscriptionBoxService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionBoxServiceImpl implements SubscriptionBoxService {
    @Override
    public ResponseEntity<List<SubscriptionBox>> getAllBoxes() {
        return null;
    }

    @Override
    public ResponseEntity<List<SubscriptionBox>> getAvailableBoxes() {
        return null;
    }
}