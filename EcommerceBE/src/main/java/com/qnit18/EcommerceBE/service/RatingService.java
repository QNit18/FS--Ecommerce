package com.qnit18.EcommerceBE.service;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.model.Rating;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.request.RatingRequest;

import java.util.List;

public interface RatingService {
    Rating crateRating(User user , RatingRequest req) throws ProductException;
    List<Rating> getProductsRating(Long productId);
}
