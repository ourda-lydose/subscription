package id.ac.ui.cs.advprog.subscription.model;

import id.ac.ui.cs.advprog.subscription.enumeration.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscription.enumeration.SubscriptionType;
import id.ac.ui.cs.advprog.subscription.mock.Box;

public class Subscription {
    String subscriptionID;
    SubscriptionType subscriptionType;
    SubscriptionStatus subscriptionStatus;
    Box subscribedBox;
}