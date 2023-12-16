package com.qnit18.EcommerceBE.controller;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.exception.UserException;
import com.qnit18.EcommerceBE.model.Review;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.request.ReviewRequest;
import com.qnit18.EcommerceBE.service.ReviewService;
import com.qnit18.EcommerceBE.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Đánh giá ", description = "Tạo đánh giá , Tìm kiếm đánh giá theo Id sản phẩm ")
public class ReviewController {

    private ReviewService reviewService;
    private UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping("/crate")
    public ResponseEntity<Review> createRating(
            @RequestBody ReviewRequest req,
            @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        Review review = reviewService.createReview(user, req);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsRating(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
        User user = userService.findUserProfileByJwt(jwt);
        List<Review> ratings = reviewService.getALlProductsReview(productId);

        return new ResponseEntity<>(ratings, HttpStatus.CREATED);
    }
}

