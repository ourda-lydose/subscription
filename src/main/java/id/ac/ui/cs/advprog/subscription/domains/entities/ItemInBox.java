package id.ac.ui.cs.advprog.subscription.domains.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ItemInBox")
public class ItemInBox {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idItemInBox;

    private String itemId;

    private int quantity;

    @ManyToOne @JoinColumn(name="subscriptionbox_id", nullable=false)
    private SubscriptionBox subscriptionbox;
}