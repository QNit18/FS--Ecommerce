package com.qnit18.EcommerceBE.service;

import com.qnit18.EcommerceBE.exception.OrderException;
import com.qnit18.EcommerceBE.model.Address;
import com.qnit18.EcommerceBE.model.Order;
import com.qnit18.EcommerceBE.model.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, Address shippingAddress);

    Order findOrderById(Long orderId) throws OrderException;

    List<Order> userOrderHistory(Long userId);

    Order placedOrder(Long orderId) throws OrderException;

    Order confirmedOrder(Long orderId) throws OrderException;

    Order shippedOrder(Long orderId) throws OrderException;

    Order deliveredOrder(Long orderId) throws OrderException;

    Order cancelOrder(Long orderId) throws OrderException;

    List<Order> getAllOrder();

    void deleteOrder(Long orderId) throws OrderException;
}

