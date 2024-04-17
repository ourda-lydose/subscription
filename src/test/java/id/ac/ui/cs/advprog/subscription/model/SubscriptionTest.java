package id.ac.ui.cs.advprog.subscription.model;

import id.ac.ui.cs.advprog.subscription.enumeration.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscription.enumeration.SubscriptionType;
import id.ac.ui.cs.advprog.subscription.mock.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {

    private Subscription mockSubscription;
    private String subscriptionId;
    private Box subscribedBox;

    @BeforeEach
    void setUp() {
        mockSubscription = new Subscription("subscription-1", SubscriptionType.MONTHLY, SubscriptionStatus.PENDING, subscribedBox);
    }

    @Test
    void testCreateSubscription() {
        Subscription subscription = new Subscription("subscription-1", SubscriptionType.MONTHLY, SubscriptionStatus.PENDING, subscribedBox);

        assertEquals("subscription-1", subscription.getSubscriptionID());
        assertEquals(SubscriptionType.MONTHLY, subscription.getSubscriptionType());
        assertEquals(SubscriptionStatus.PENDING, subscription.getSubscriptionStatus());
        assertEquals(subscribedBox, subscription.getSubscribedBox());
    }

    @Test
    void testCreateInvalidRating() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Subscription(null, SubscriptionType.MONTHLY, SubscriptionStatus.PENDING, subscribedBox);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Subscription("subscription-1", null, SubscriptionStatus.PENDING, subscribedBox);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Subscription("subscription-1", SubscriptionType.MONTHLY, null, subscribedBox);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Subscription("subscription-1", SubscriptionType.MONTHLY, SubscriptionStatus.PENDING, subscribedBox);
        });
    }

}