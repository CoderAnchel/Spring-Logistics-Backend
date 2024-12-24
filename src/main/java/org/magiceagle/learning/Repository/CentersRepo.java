package org.magiceagle.learning.Repository;

import org.magiceagle.learning.Entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentersRepo extends JpaRepository<Center, Long> {
    // This is a marker interface
    @Query("SELECT c FROM Center c WHERE c.capacity = :capacity AND c.currentLoad < c.maxCapacity")
    List<Center> findAvilableCenters(@Param("capacity") String capacity);
}