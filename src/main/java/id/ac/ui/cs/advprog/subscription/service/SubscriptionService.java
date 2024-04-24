package id.ac.ui.cs.advprog.subscription.service;
import java.util.List;
import id.ac.ui.cs.advprog.subscription.model.Subscription;

public interface SubscriptionService {
    public Subscription create(Subscription subscription);
    public List<Subscription> findAll();
    Subscription findById(String subscriptionId);
    public void update(String subscriptionId, Subscription subscription);
    public void deleteSubscriptionById(String subscriptionId);
}

