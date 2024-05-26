package id.ac.ui.cs.advprog.subscription.repository;import id.ac.ui.cs.advprog.subscription.domains.entities.Subscription;import id.ac.ui.cs.advprog.subscription.domains.enums.SubscriptionStatus;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;import java.util.ArrayList;import java.util.Iterator;import java.util.List;@Repositorypublic interface SubscriptionRepository extends JpaRepository<Subscription, String> {    static int id = 0;    List<Subscription> subscriptionData = new ArrayList<>();    public default Iterator<Subscription> findAllSubscription() {        return subscriptionData.iterator();    }    List<Subscription> findSubscriptionById(String subscriptionId);}