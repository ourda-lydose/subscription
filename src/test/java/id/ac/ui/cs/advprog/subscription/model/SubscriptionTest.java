// Adjusted SubscriptionTest class
package id.ac.ui.cs.advprog.subscription.model;

import id.ac.ui.cs.advprog.subscription.domains.entities.Subscription;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionType;
import id.ac.ui.cs.advprog.subscription.domains.models.SubscriptionData;
import id.ac.ui.cs.advprog.subscription.domains.models.responses.ResponseData;
import id.ac.ui.cs.advprog.subscription.repository.SubscriptionRepository;
import id.ac.ui.cs.advprog.subscription.service.implementations.SubscriptionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    private SubscriptionData subscriptionData;

    @BeforeEach
    void setUp() {
        subscriptionData = new SubscriptionData();
        subscriptionData.setType(SubscriptionType.MONTHLY);
        subscriptionData.setSubscriptionBox(new ArrayList<>());
        subscriptionData.setStartDate(null);
    }

    @Test
    void testGetSubscriptions() {
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription());
        subscriptions.add(new Subscription());
        when(subscriptionRepository.findAll()).thenReturn(subscriptions);

        ResponseEntity<List<Subscription>> responseEntity = subscriptionService.getSubscriptions();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subscriptions, responseEntity.getBody());
    }

    @Test
    void testGetSubscriptionsByStatus() {
        String statusString = "PENDING";
        List<Subscription> subscriptions = new ArrayList<>();
        Subscription subscription1 = new Subscription();
        subscription1.setStatusString(statusString);
        Subscription subscription2 = new Subscription();
        subscription2.setStatusString("ANOTHER_STATUS"); // Different status
        subscriptions.add(subscription1);
        subscriptions.add(subscription2);
        when(subscriptionRepository.findAll()).thenReturn(subscriptions);

        ResponseEntity<List<Subscription>> responseEntity = subscriptionService.getSubscriptionsByStatus(statusString);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).size()); // Only one subscription should match the status
        assertEquals(subscription1, responseEntity.getBody().get(0)); // Verify the correct subscription is returned
    }
}
