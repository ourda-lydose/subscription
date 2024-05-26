package id.ac.ui.cs.advprog.subscription.domains.states;import id.ac.ui.cs.advprog.subscription.domains.entities.Subscription;public class SubscribeState implements SubscriptionState {    private final Subscription subscription;    public SubscribeState(Subscription subscription) {        this.subscription = subscription;    }    @Override    public void activateSubscription() {        subscription.setSubscriptionState(new SubscribeState(subscription));    }    @Override    public void cancelSubscription() {        subscription.setSubscriptionState(new CancelState(subscription));    }    @Override    public void pendingSubscription() {        throw new IllegalStateException("Can't pending an activated sub!");    }}