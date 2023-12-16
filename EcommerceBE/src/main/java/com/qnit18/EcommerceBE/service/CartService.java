package com.qnit18.EcommerceBE.service;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.model.Cart;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.request.AddItemRequest;

public interface CartService {

    Cart createCart(User user);

    String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    Cart findUserCart(Long userId);
}