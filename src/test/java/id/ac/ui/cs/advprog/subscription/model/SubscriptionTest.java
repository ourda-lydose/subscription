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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    public void testCreateSubscription() {
        SubscriptionData subscriptionData = new SubscriptionData();
        subscriptionData.setType(SubscriptionType.MONTHLY);
        subscriptionData.setStartDate(LocalDate.now());

        when(subscriptionRepository.save(any(Subscription.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<Subscription> response = subscriptionService.createSubscription(subscriptionData);

        assertNotNull(response.getBody());
        assertEquals(SubscriptionType.MONTHLY, response.getBody().getType());
    }

    @Test
    public void testGetSubscriptionById() {
        Subscription subscription = new Subscription();
        subscription.setId("ea6e2877-2dc4-4b09-90fd-e3b105a419cb");
        subscription.setCode("MTH-ea6e2877-2dc4-4b09-90fd-e3b105a419cb");
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1));
        subscription.setStatusString("PENDING");

        when(subscriptionRepository.findById("ea6e2877-2dc4-4b09-90fd-e3b105a419cb")).thenReturn(Optional.of(subscription));

        ResponseEntity<Subscription> response = subscriptionService.getSubscriptionById("MTH-ea6e2877-2dc4-4b09-90fd-e3b105a419cb");

        assertNotNull(response.getBody());
        assertEquals("MTH-ea6e2877-2dc4-4b09-90fd-e3b105a419cb", response.getBody().getCode());
    }

    @Test
    public void testGetSubscriptionHistory() {
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription());
        subscriptions.add(new Subscription());

        when(subscriptionRepository.findAllByEndDateBefore(any())).thenReturn(subscriptions);

        ResponseEntity<List<Subscription>> response = subscriptionService.getSubscriptionHistory(null);

        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetSubscriptionById_InactiveSubscription() {
        Subscription subscription = new Subscription();
        subscription.setId("ea6e2877-2dc4-4b09-90fd-e3b105a419cb");
        subscription.setCode("MTH-ea6e2877-2dc4-4b09-90fd-e3b105a419cb");
        subscription.setStartDate(LocalDate.now().minusMonths(2));
        subscription.setEndDate(LocalDate.now().minusMonths(1));
        subscription.setStatusString("EXPIRED");

        when(subscriptionRepository.findById("ea6e2877-2dc4-4b09-90fd-e3b105a419cb")).thenReturn(Optional.of(subscription));

        ResponseEntity<Subscription> response = subscriptionService.getSubscriptionById("MTH-ea6e2877-2dc4-4b09-90fd-e3b105a419cb");

        assertNotNull(response.getBody());
        assertEquals("MTH-ea6e2877-2dc4-4b09-90fd-e3b105a419cb", response.getBody().getCode());
        assertEquals("EXPIRED", response.getBody().getStatusString());
    }

    @Test
    public void testGetSubscriptionById_WithDifferentIdFormat() {
        Subscription subscription = new Subscription();
        subscription.setId("another-format-id");
        subscription.setCode("MTH-another-format-id");
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1));
        subscription.setStatusString("ACTIVE");

        when(subscriptionRepository.findById("another-format-id")).thenReturn(Optional.of(subscription));

        ResponseEntity<Subscription> response = subscriptionService.getSubscriptionById("MTH-another-format-id");

        assertNotNull(response.getBody());
        assertEquals("MTH-another-format-id", response.getBody().getCode());
    }

    @Test
    public void testGetSubscriptionById_InvalidStatus() {
        Subscription subscription = new Subscription();
        subscription.setId("ea6e2877-2dc4-4b09-90fd-e3b105a419cb");
        subscription.setCode("MTH-ea6e2877-2dc4-4b09-90fd-e3b105a419cb");
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1));
        subscription.setStatusString("INVALID_STATUS");

        when(subscriptionRepository.findById("ea6e2877-2dc4-4b09-90fd-e3b105a419cb")).thenReturn(Optional.of(subscription));

        ResponseEntity<Subscription> response = subscriptionService.getSubscriptionById("MTH-ea6e2877-2dc4-4b09-90fd-e3b105a419cb");

        assertNotNull(response.getBody());
        assertEquals("MTH-ea6e2877-2dc4-4b09-90fd-e3b105a419cb", response.getBody().getCode());
        assertEquals("INVALID_STATUS", response.getBody().getStatusString());
    }

}
