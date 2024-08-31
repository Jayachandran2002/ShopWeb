package com.SpringBoot.ShopWebApp.Controller;

import com.SpringBoot.ShopWebApp.Model.Product;
import com.SpringBoot.ShopWebApp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if(product != null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id){
        Product product = productService.getProductById(id);
        byte[] imageFile = product.getImageData();

        if (imageFile != null)
            return ResponseEntity.ok(imageFile);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try{
        Product product1 = productService.addProduct(product,imageFile);
        return ResponseEntity.ok(product);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id,
                                                @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile ) throws IOException {

        boolean updated = productService.updateProductById(id,product,imageFile);
        if (updated)
            return ResponseEntity.ok("Update Successful");
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        boolean deleted = productService.deleteProductById(id);
        if (deleted)
            return ResponseEntity.ok("Product Delete Successful");
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = productService.searchProducts(keyword);
        if (products != null)
            return ResponseEntity.ok(products);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
