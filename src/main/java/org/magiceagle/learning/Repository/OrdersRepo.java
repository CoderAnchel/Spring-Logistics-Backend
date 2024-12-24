package org.magiceagle.learning.Repository;

import org.magiceagle.learning.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Order, Long> {
    // This is a marker interface
    @Query("SELECT o FROM Order o WHERE o.Status = 'Pending'")
    List<Order> findPending();
}
