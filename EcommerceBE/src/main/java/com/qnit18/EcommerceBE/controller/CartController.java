package com.qnit18.EcommerceBE.controller;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.exception.UserException;
import com.qnit18.EcommerceBE.model.Cart;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.request.AddItemRequest;
import com.qnit18.EcommerceBE.response.ApiResponse;
import com.qnit18.EcommerceBE.service.CartService;
import com.qnit18.EcommerceBE.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Giỏ hàng", description = "Tìm kiếm giỏ hàng người dùng, thêm sản phẩm vào giỏ hàng")
public class CartController {

    private CartService cartService;
    private UserService userService;

    @Autowired
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{

        User user=userService.findUserProfileByJwt(jwt);

        String check = cartService.addCartItem(user.getId(), req);

        if (check == null) {
            ApiResponse res= new ApiResponse("Item Added To Cart Failure",false);
            return new ResponseEntity<ApiResponse>(res,HttpStatus.BAD_REQUEST);
        }
        ApiResponse res= new ApiResponse("Item Added To Cart Successfully",true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.CREATED);
    }
}

