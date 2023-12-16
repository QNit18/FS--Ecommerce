package com.qnit18.EcommerceBE.impletation;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.model.Product;
import com.qnit18.EcommerceBE.model.Review;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.repository.ProductRepository;
import com.qnit18.EcommerceBE.repository.ReviewRepository;
import com.qnit18.EcommerceBE.request.ReviewRequest;
import com.qnit18.EcommerceBE.service.ProductService;
import com.qnit18.EcommerceBE.service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {
    private ReviewRepository reviewRepository;
    private ProductService productService;
    private ProductRepository productRepository;

    @Autowired
    public ReviewServiceImp(ReviewRepository reviewRepository,
                            ProductService productService,
                            ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Override
    public Review createReview(User user, ReviewRequest req) throws ProductException {
        Product product = productService.findProductById(req.getProductId());

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getALlProductsReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}