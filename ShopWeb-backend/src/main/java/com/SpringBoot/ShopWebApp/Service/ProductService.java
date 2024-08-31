package com.SpringBoot.ShopWebApp.Service;

import com.SpringBoot.ShopWebApp.Model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product addProduct(Product product, MultipartFile image) throws IOException;

    boolean deleteProductById(Long id);

    boolean updateProductById(Long id, Product product, MultipartFile imageFile) throws IOException;

    List<Product> searchProducts(String keyword);
}
