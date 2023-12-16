package com.qnit18.EcommerceBE.impletation;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.model.Product;
import com.qnit18.EcommerceBE.model.Rating;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.repository.RatingRepository;
import com.qnit18.EcommerceBE.request.RatingRequest;
import com.qnit18.EcommerceBE.service.ProductService;
import com.qnit18.EcommerceBE.service.RatingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImp implements RatingService {

    private RatingRepository ratingRepository;
    private ProductService productService;

    @Autowired
    public RatingServiceImp(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating crateRating(User user, RatingRequest req) throws ProductException {
        Product product = productService.findProductById(req.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
