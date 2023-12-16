package com.qnit18.EcommerceBE.controller;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.model.Product;
import com.qnit18.EcommerceBE.request.CreateProductRequest;
import com.qnit18.EcommerceBE.response.ApiResponse;
import com.qnit18.EcommerceBE.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
@Tag(name = "Quản lý sản phẩm ", description = "")
public class AdminProductController {

    private ProductService productService;

    @Autowired
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req){
        Product product = productService.createProduct(req);

        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMulProduct(@RequestBody CreateProductRequest [] req){
        for (CreateProductRequest r : req){
            productService.createProduct(r);
        }
        ApiResponse res = new ApiResponse();
        res.setMessage("All of product create successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {
        productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Product deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct(){

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product req,
                                                 @PathVariable Long productId) throws ProductException{
        Product product = productService.updateProduct(productId,req);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }


}
