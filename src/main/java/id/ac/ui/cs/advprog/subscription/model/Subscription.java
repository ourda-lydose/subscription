package id.ac.ui.cs.advprog.subscription.model;

import id.ac.ui.cs.advprog.subscription.enumeration.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscription.enumeration.SubscriptionType;
import id.ac.ui.cs.advprog.subscription.mock.Box;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subscription {

    String subscriptionID;
    SubscriptionType subscriptionType;
    SubscriptionStatus subscriptionStatus;
    Box subscribedBox;

    // For some reason Getter and Setter in lombok don't work in my computer (which is an issue that
    // persists since tutorial 1 for some reason), so i'm using the traditional getter and setter
    // method in the meantime

    public void setSubscriptionID(String subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public Box getSubscribedBox() {
        return subscribedBox;
    }

    public Subscription(String subscriptionID, SubscriptionType subscriptionType,
                        SubscriptionStatus subscriptionStatus, Box subscribedBox) {

        if (subscriptionID == null || subscriptionID.isEmpty()) {
            throw new IllegalArgumentException("Subscription ID cannot be null or empty");
        }

        if (subscriptionType == null) {
            throw new IllegalArgumentException("Subscription type cannot be null");
        }

        if (subscriptionStatus == null) {
            throw new IllegalArgumentException("Subscription status cannot be null");
        }

        if (subscribedBox == null) {
            throw new IllegalArgumentException("Subscribed box cannot be null");
        }

        this.subscriptionID = subscriptionID;
        this.subscriptionType = subscriptionType;
        this.subscriptionStatus = subscriptionStatus;
        this.subscribedBox = subscribedBox;
    }
}