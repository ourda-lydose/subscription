package id.ac.ui.cs.advprog.subscription.domains.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionType;
import id.ac.ui.cs.advprog.subscription.domains.states.SubscriptionState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "subscriptions")
public class Subscription {

    @Id
    private String id;
    private SubscriptionType type;
    private SubscriptionStatus status;

    @OneToMany(mappedBy = "subscription", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<SubscriptionBox> subscriptionBox;

//    private SubscriptionState subscriptionState;

    public Subscription(String subscriptionID, SubscriptionType subscriptionType,
                        SubscriptionStatus subscriptionStatus, SubscriptionBox subscriptionBox) {

        if (subscriptionID == null || subscriptionID.isEmpty()) {
            throw new IllegalArgumentException("Subscription ID cannot be null or empty");
        }

        if (subscriptionType == null) {
            throw new IllegalArgumentException("Subscription type cannot be null");
        }

        if (subscriptionStatus == null) {
            throw new IllegalArgumentException("Subscription status cannot be null");
        }

        if (subscriptionBox == null) {
            throw new IllegalArgumentException("Subscribed box cannot be null");
        }

        this.id = subscriptionID;
        this.type = subscriptionType;
        this.status = subscriptionStatus;
//        this.subscriptionBox = subscriptionBox;
//        this.subscriptionState = new PendingState(this);
    }

//    public void activateSubscription() {
//        this.subscriptionState.activateSubscription();
//    }
//
//    public void cancelSubscription() { this.subscriptionState.cancelSubscription(); }
}