package id.ac.ui.cs.advprog.subscription.domains.entities;

import id.ac.ui.cs.advprog.subscription.domains.BoxBuilder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="subscriptionbox")
public class SubscriptionBox {
    @Id
    private String id;
    private String name;
    private String image;
    private String description;
    private double price;

    @OneToMany(mappedBy="subscriptionbox", fetch = FetchType.EAGER)
    private Set<ItemInBox> itemInBoxList;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    public SubscriptionBox(BoxBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.image = builder.getImage();
    }
}