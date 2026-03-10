package com.kafka.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
