package com.qnit18.EcommerceBE.impletation;

import com.qnit18.EcommerceBE.model.OrderItem;
import com.qnit18.EcommerceBE.repository.OrderItemRepository;
import com.qnit18.EcommerceBE.service.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImp implements OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImp(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
