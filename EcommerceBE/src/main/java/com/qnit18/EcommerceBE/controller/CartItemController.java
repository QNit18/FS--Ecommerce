package com.qnit18.EcommerceBE.controller;


import com.qnit18.EcommerceBE.exception.CartItemException;
import com.qnit18.EcommerceBE.exception.UserException;
import com.qnit18.EcommerceBE.model.CartItem;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.response.ApiResponse;
import com.qnit18.EcommerceBE.service.CartItemService;
import com.qnit18.EcommerceBE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {
    private CartItemService cartItemService;
    private UserService userService;

    @Autowired
    public CartItemController(CartItemService cartItemService, UserService userService) {
        this.cartItemService = cartItemService;
        this.userService = userService;
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
                                                      @RequestHeader("Authorization") String jwt) throws UserException, CartItemException{
        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Remove item from cart successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long cartItemId,
                                                   @RequestBody CartItem cartItem,
                                                   @RequestHeader("Authorization") String jwt)
        throws CartItemException,UserException {
        User user = userService.findUserProfileByJwt(jwt);

        CartItem updateCartItem =cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

        return new ResponseEntity<>(updateCartItem, HttpStatus.ACCEPTED);
    }
}
