package com.kafka.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.order.entity.TrackingEvent;

@Repository
public interface TrackingRepo extends JpaRepository<TrackingEvent, Long> {

    // Find all tracking events for a specific order, sorted by timestamp
    List<TrackingEvent> findByOrderIdOrderByTimestampAsc(Long orderId);
}
