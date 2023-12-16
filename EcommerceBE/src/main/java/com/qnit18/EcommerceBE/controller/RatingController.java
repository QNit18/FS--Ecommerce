package com.qnit18.EcommerceBE.controller;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.exception.UserException;
import com.qnit18.EcommerceBE.model.Rating;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.request.RatingRequest;
import com.qnit18.EcommerceBE.service.RatingService;
import com.qnit18.EcommerceBE.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@Tag(name = "Xếp hạng", description = "Tạo xếp hạng, Tìm kiếm xếp hạng theo Id sản phẩm ")
public class RatingController {

    private RatingService ratingService;
    private UserService userService;

    @Autowired
    public RatingController(RatingService ratingService, UserService userService) {
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @PostMapping("/crate")
    public ResponseEntity<Rating> createRating(
            @RequestBody RatingRequest req,
            @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        Rating rating = ratingService.crateRating(user, req);

        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
        User user = userService.findUserProfileByJwt(jwt);
        List<Rating> ratings = ratingService.getProductsRating(productId);

        return new ResponseEntity<>(ratings, HttpStatus.CREATED);
    }
}
