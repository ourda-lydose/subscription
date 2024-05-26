package id.ac.ui.cs.advprog.subscription.model;

import id.ac.ui.cs.advprog.subscription.domains.entities.Subscription;
import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionType;
import id.ac.ui.cs.advprog.subscription.domains.states.PendingState;
import id.ac.ui.cs.advprog.subscription.mock.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {

    private Subscription mockSubscription;
    private String subscriptionId;
    private List<SubscriptionBox> subscriptionBox;

    @BeforeEach
    void setUp() {
        subscriptionBox = new ArrayList<>();
        mockSubscription = new Subscription("subscription-1", SubscriptionType.MONTHLY, SubscriptionStatus.PENDING, subscriptionBox);
    }

    @Test
    void testCreateSubscription() {
        Subscription subscription = new Subscription("subscription-1", SubscriptionType.MONTHLY, SubscriptionStatus.PENDING, subscriptionBox);

        assertEquals("subscription-1", subscription.getId());
        assertEquals(SubscriptionType.MONTHLY, subscription.getType());
        assertTrue(subscription.getSubscriptionState() instanceof PendingState);
        assertEquals(subscriptionBox, subscription.getSubscriptionBox());
    }

    @Test
    void testCreateInvalidSubscription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Subscription(null, SubscriptionType.MONTHLY, SubscriptionStatus.PENDING, subscriptionBox);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Subscription("subscription-1", null, SubscriptionStatus.PENDING, subscriptionBox);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Subscription("subscription-1", SubscriptionType.MONTHLY, null, subscriptionBox);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Subscription("subscription-1", SubscriptionType.MONTHLY, SubscriptionStatus.PENDING, null);
        });
    }
}