package com.kafka.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.inventory.entity.InventoryDLQ;

public interface InventoryDLQRepository extends JpaRepository<InventoryDLQ, String> {
}