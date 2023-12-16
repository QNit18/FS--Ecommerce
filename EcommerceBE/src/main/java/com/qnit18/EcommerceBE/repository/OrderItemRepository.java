package com.qnit18.EcommerceBE.repository;

import com.qnit18.EcommerceBE.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
