package com.SpringBoot.ShopWebApp.Repository;

import com.SpringBoot.ShopWebApp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("Select p from Product p where " +
            "Lower(p.name) Like (Concat('%',:keyword,'%')) or " +
            "Lower(p.description) Like (Concat('%',:keyword,'%')) or " +
            "Lower(p.brand) Like (Concat('%',:keyword,'%')) or " +
            "Lower(p.category) Like (Concat('%',:keyword,'%'))")
    List<Product> searchProduct(String keyword);
}
