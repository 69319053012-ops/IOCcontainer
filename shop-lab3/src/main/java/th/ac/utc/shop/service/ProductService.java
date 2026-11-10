package th.ac.utc.shop.service;

import th.ac.utc.shop.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product save(Product product);
}
