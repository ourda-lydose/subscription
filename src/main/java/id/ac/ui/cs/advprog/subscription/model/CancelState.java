package id.ac.ui.cs.advprog.subscription.model;

import id.ac.ui.cs.advprog.subscription.model.SubscriptionState;
import id.ac.ui.cs.advprog.subscription.model.Subscription;

import java.util.concurrent.Flow;

public class CancelState implements SubscriptionState {
    private final Subscription subscription;

    public CancelState(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void activateSubscription() {
        throw new IllegalStateException("can't activate a cancelled sub!");
    }

    @Override
    public void cancelSubscription() {

    }

}