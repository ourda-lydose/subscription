package id.ac.ui.cs.advprog.subscription.domains.models;import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionStatus;import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionType;import lombok.Getter;import lombok.Setter;import java.time.LocalDate;import java.util.List;@Getter @Setterpublic class SubscriptionData {    private SubscriptionType type;    private SubscriptionStatus statusString;    private LocalDate startDate;    private List<SubscriptionBox> subscriptionBox;}