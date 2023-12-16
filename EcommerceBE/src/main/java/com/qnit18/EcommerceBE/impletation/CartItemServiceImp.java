package com.qnit18.EcommerceBE.impletation;

import com.qnit18.EcommerceBE.exception.CartItemException;
import com.qnit18.EcommerceBE.exception.UserException;
import com.qnit18.EcommerceBE.model.Cart;
import com.qnit18.EcommerceBE.model.CartItem;
import com.qnit18.EcommerceBE.model.Product;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.repository.CartItemRepository;
import com.qnit18.EcommerceBE.repository.CartRepository;
import com.qnit18.EcommerceBE.service.CartItemService;
import com.qnit18.EcommerceBE.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartItemServiceImp implements CartItemService {

    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;

    @Autowired
    public CartItemServiceImp(CartItemRepository cartItemRepository,
                              UserService userService,
                              CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartItem createdCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

        CartItem item=findCartItemById(id);
        User user=userService.findUserById(item.getUserId());

        if(user.getId().equals(userId)) {

            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getQuantity()*item.getProduct().getDiscountedPrice());

            return cartItemRepository.save(item);
        }
        else {
            throw new CartItemException("You can't update  another users cart_item");
        }
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return cartItemRepository.isCartItemExist(cart, product, size, userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem=findCartItemById(cartItemId);
        User user=userService.findUserById(cartItem.getUserId());
        User reqUser=userService.findUserById(userId);

        if(user.getId().equals(reqUser.getId())) {
            cartItemRepository.deleteById(cartItem.getId());
        }else {
            throw new UserException("you can't remove anothor users item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new CartItemException("CartItem not found with id: " + cartItemId);
    }
}

