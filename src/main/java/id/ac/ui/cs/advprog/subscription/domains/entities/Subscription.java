package id.ac.ui.cs.advprog.subscription.domains.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionType;
import id.ac.ui.cs.advprog.subscription.domains.states.SubscribeState;
import id.ac.ui.cs.advprog.subscription.domains.states.CancelState;
import id.ac.ui.cs.advprog.subscription.domains.states.PendingState;
import id.ac.ui.cs.advprog.subscription.domains.states.SubscriptionState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "subscriptions")
public class Subscription {

    @Id
    private String id;

    private String code;

    @Column(name = "type")
    private SubscriptionType type;

    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @OneToMany(mappedBy = "subscription", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<SubscriptionBox> subscriptionBox;

    @Transient
    @JsonIgnore
    private SubscriptionState subscriptionState;

    @Column(name = "status_string")
    private String statusString;

    public Subscription(String subscriptionID, SubscriptionType subscriptionType,
                        SubscriptionStatus subscriptionStatus, List<SubscriptionBox> subscriptionBox) {

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
        this.subscriptionBox = subscriptionBox;
//        this.subscriptionState = new PendingState(this);
    }

    @PostLoad
    private void initStatus() {
        switch (statusString) {
            case "SUBSCRIBED":
                this.subscriptionState = new SubscribeState(this);
                break;
            case "PENDING":
                this.subscriptionState = new PendingState(this);
                break;
            case "CANCELLED":
                this.subscriptionState = new CancelState(this);
                break;
            default:
                throw new IllegalStateException("Invalid status string: " + statusString);
        }
    }


    public void activateSubscription() {
        this.subscriptionState.activateSubscription();
    }

    public void cancelSubscription() {
        this.subscriptionState.cancelSubscription();
    }

    public void pendingSubscription(){
        this.subscriptionState.pendingSubscription();
    }
}