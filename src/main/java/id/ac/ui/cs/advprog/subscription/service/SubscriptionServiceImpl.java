package id.ac.ui.cs.advprog.subscription.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.subscription.model.Subscription;

public class SubscriptionServiceImpl implements SubscriptionService {
    private Map<String, Subscription> subscriptionMap;

    public SubscriptionServiceImpl() {
        this.subscriptionMap = new HashMap<>();
    }

    @Override
    public Subscription create(Subscription subscription) {
        subscriptionMap.put(subscription.getSubscriptionID(), subscription);
        return subscription;
    }

    @Override
    public List<Subscription> findAll() {
        return new ArrayList<>(subscriptionMap.values());
    }

    @Override
    public Subscription findById(String subscriptionId) {
        return subscriptionMap.get(subscriptionId);
    }

    @Override
    public void update(String subscriptionId, Subscription subscription) {
        if (subscriptionMap.containsKey(subscriptionId)) {
            subscriptionMap.put(subscriptionId, subscription);
        }
    }

    @Override
    public void deleteSubscriptionById(String subscriptionId) {
        subscriptionMap.remove(subscriptionId);
    }
}
