package com.qnit18.EcommerceBE.controller;

import com.qnit18.EcommerceBE.exception.OrderException;
import com.qnit18.EcommerceBE.exception.UserException;
import com.qnit18.EcommerceBE.model.Address;
import com.qnit18.EcommerceBE.model.Order;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.service.OrderService;
import com.qnit18.EcommerceBE.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Hóa đơn sản phẩm", description = "Tạo hóa đơn , lịch sử mua hàng, tìm kiếm hóa đơn theo ID người dùng ")
public class OrderController {

    private OrderService orderService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingaddress,
                                             @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.createOrder(user, shippingaddress);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(
            @RequestHeader("Authorization") String jwt)throws UserException{
        User user = userService.findUserProfileByJwt(jwt);
        List<Order> orders = orderService.userOrderHistory(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Order> findOrderById(
            @PathVariable("Id") Long orderId,
            @RequestHeader("Authorization") String jwt) throws OrderException, UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}