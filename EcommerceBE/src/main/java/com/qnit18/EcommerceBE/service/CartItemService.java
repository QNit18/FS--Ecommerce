package com.qnit18.EcommerceBE.service;

import com.qnit18.EcommerceBE.exception.CartItemException;
import com.qnit18.EcommerceBE.exception.UserException;
import com.qnit18.EcommerceBE.model.Cart;
import com.qnit18.EcommerceBE.model.CartItem;
import com.qnit18.EcommerceBE.model.Product;

public interface CartItemService {
    CartItem createdCartItem(CartItem cartItem);

    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
