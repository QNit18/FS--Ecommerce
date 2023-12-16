package com.qnit18.EcommerceBE.impletation;

import com.qnit18.EcommerceBE.exception.ProductException;
import com.qnit18.EcommerceBE.model.Category;
import com.qnit18.EcommerceBE.model.Product;
import com.qnit18.EcommerceBE.repository.CategoryRepository;
import com.qnit18.EcommerceBE.repository.ProductRepository;
import com.qnit18.EcommerceBE.request.CreateProductRequest;
import com.qnit18.EcommerceBE.service.ProductService;
import com.qnit18.EcommerceBE.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository,
                             UserService userService,
                             CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductRequest req) {
        Category topLevel = categoryRepository.findByName(req.getTopLavelCategory());

        if(topLevel==null){
            Category topLavelCategory = new Category();
            topLavelCategory.setName(req.getTopLavelCategory());
            topLavelCategory.setLevel(1);

            topLevel = categoryRepository.save(topLavelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondLavelCategory(), topLevel.getName());
        if (secondLevel == null){
            Category secondCategory = new Category();
            secondCategory.setName(req.getSecondLavelCategory());
            secondCategory.setParentCategory(topLevel);
            secondCategory.setLevel(2);

            secondLevel = categoryRepository.save(secondCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdLavelCategory(), secondLevel.getName());
        if (thirdLevel == null){
            Category thirdCategory = new Category();
            thirdCategory.setName(req.getThirdLavelCategory());
            thirdCategory.setParentCategory(secondLevel);
            thirdCategory.setLevel(3);

            thirdLevel = categoryRepository.save(thirdCategory);
        }

        Product product = new Product();

        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountPersent(req.getDiscountNow());
        product.setPrice(req.getPrice());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) throws ProductException {
        Product productF = findProductById(productId);
        productF.getSizes().clear();
        productRepository.delete(productF);

    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product productF = findProductById(productId);

        if(req.getQuantity() != 0){
            productF.setQuantity(req.getQuantity());
        }

        return productRepository.save(productF);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> opt =productRepository.findById(id);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new ProductException("Product not found with id - " + id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
                                       Integer maxPrice, Integer minDiscount, String sort, String stock,
                                       Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

        // p1:read, p2:white, p3:yellow,
        if (!colors.isEmpty()){
            products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }

        if (stock!=null){
            if(stock.equals("in_stock")){
                products = products.stream().filter(p -> p.getQuantity()>0).collect(Collectors.toList());
            }else if (stock.equals("out_of_stock")){
                products = products.stream().filter(p -> p.getQuantity()<1).collect(Collectors.toList());
            }
        }

        // Tính trang bắt đầu và kết thúc
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);

        return new PageImpl<>(pageContent,pageable,products.size());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchProduct(String query) {
        return productRepository.searchProduct(query);
    }
}

