package com.qnit18.EcommerceBE.service;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.model.Review;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    Review createReview(User user, ReviewRequest req) throws ProductException;
    List<Review> getALlProductsReview(Long productId);
}