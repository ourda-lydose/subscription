package id.ac.ui.cs.advprog.subscription.model;

import id.ac.ui.cs.advprog.subscription.model.SubscriptionState;
import id.ac.ui.cs.advprog.subscription.model.Subscription;

import java.util.concurrent.Flow;

public class ActivateState implements SubscriptionState {
    private final Subscription subscription;

    public ActivateState(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void activateSubscription() {

    }

    @Override
    public void cancelSubscription() {
        throw new IllegalStateException("can't cancel an activated sub!");
    }
}