package com.qnit18.EcommerceBE.service;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.model.Product;
import com.qnit18.EcommerceBE.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest req);

    Product findProductById(Long id) throws ProductException;

    void deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, Product req) throws ProductException;

    List<Product> getAllProducts();


    List<Product> findProductByCategory(String category);

    Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
                                Integer maxPrice, Integer minDiscount, String sort, String stock,
                                Integer pageNumber, Integer pageSize);

    List<Product> searchProduct(String query);

}
