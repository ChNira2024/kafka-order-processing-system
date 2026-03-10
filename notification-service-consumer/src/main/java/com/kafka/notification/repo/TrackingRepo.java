package com.kafka.notification.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.notification.entity.TrackingEvent;

@Repository
public interface TrackingRepo extends JpaRepository<TrackingEvent, Long> {
    List<TrackingEvent> findByOrderIdOrderByTimestampAsc(Long orderId);
}
