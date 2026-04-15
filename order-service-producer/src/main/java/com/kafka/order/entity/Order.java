package com.kafka.order.entity;

import java.time.LocalDateTime;

import com.kafka.order.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Double amount;

    @Enumerated(EnumType.STRING) //
    private OrderStatus status;
    
    private LocalDateTime createdAt = LocalDateTime.now();

	public Order(Long orderId, Double amount, OrderStatus status, LocalDateTime createdAt) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.status = status;
		this.createdAt = createdAt;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
    
}
