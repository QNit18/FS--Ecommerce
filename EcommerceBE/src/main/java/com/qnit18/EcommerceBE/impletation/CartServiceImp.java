package com.qnit18.EcommerceBE.impletation;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.model.Cart;
import com.qnit18.EcommerceBE.model.CartItem;
import com.qnit18.EcommerceBE.model.Product;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.repository.CartItemRepository;
import com.qnit18.EcommerceBE.repository.CartRepository;
import com.qnit18.EcommerceBE.request.AddItemRequest;
import com.qnit18.EcommerceBE.service.CartItemService;
import com.qnit18.EcommerceBE.service.CartService;
import com.qnit18.EcommerceBE.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartServiceImp implements CartService {

    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;
    private CartItemRepository cartItemRepository;


    @Autowired
    public CartServiceImp(CartRepository cartRepository,CartItemService cartItemService,
                                     ProductService productService, CartItemRepository cartItemRepository) {
        this.cartRepository=cartRepository;
        this.productService=productService;
        this.cartItemService=cartItemService;
        this.cartItemRepository = cartItemRepository;
    }


    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart =	cartRepository.findByUserId(userId);
        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalItem=0;
        for(CartItem cartsItem : cart.getCartItems()) {
            totalPrice+=cartsItem.getPrice();
            totalDiscountedPrice+=cartsItem.getDiscountedPrice();
            totalItem+=cartsItem.getQuantity();
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(cart.getCartItems().size());
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setDiscounte(totalPrice-totalDiscountedPrice);
        cart.setTotalItem(totalItem);

        return cartRepository.save(cart);

    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart=cartRepository.findByUserId(userId);
        Product product=productService.findProductById(req.getProductId());

        CartItem isPresent=cartItemService.isCartItemExist(cart, product, req.getSize(),userId);

        if(isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);
            cartItem.setCreateAt(LocalDateTime.now());


            int price=req.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());

            CartItem createdCartItem=cartItemService.createdCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
            return "Item Add To Cart";
        }
        return null;
    }
}
