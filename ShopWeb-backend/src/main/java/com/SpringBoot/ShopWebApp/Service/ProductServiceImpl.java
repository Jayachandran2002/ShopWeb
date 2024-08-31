package com.SpringBoot.ShopWebApp.Service;

import com.SpringBoot.ShopWebApp.Model.Product;
import com.SpringBoot.ShopWebApp.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository repository;

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return repository.save(product);
    }

    @Override
    public boolean deleteProductById(Long id) {
        Product product = repository.findById(id).orElse(null);
        if (product != null) {
            repository.deleteById(id);
            return true;
        }else
            return false;
    }

    @Override
    public boolean updateProductById(Long id, Product product, MultipartFile imageFile) throws IOException {
        Product product1 = repository.findById(id).orElse(null);
        if(product1 != null){
            product1.setName(product.getName());
            product1.setDescription(product.getDescription());
            product1.setAvailable(product.isAvailable());
            product1.setBrand(product.getBrand());
            product1.setCategory(product.getCategory());
            product1.setPrice(product.getPrice());
            product1.setQuantity(product.getQuantity());
            product1.setReleasedate(product.getReleasedate());
            product1.setImageName(imageFile.getOriginalFilename());
            product1.setImageType(imageFile.getContentType());
            product1.setImageData(imageFile.getBytes());
            repository.save(product1);
            return true;
        }else
            return false;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
       return repository.searchProduct(keyword);
    }
}
