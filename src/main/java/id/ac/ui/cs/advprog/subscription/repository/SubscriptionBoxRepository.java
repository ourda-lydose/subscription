package id.ac.ui.cs.advprog.subscription.repository;

import id.ac.ui.cs.advprog.subscription.domains.entities.SubscriptionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionBoxRepository extends JpaRepository<SubscriptionBox, String> {
    Optional<SubscriptionBox> findById(String id);
    List<SubscriptionBox> findByName(String name);
//    Optional<SubscriptionBox> findByPrice(int price);
}
