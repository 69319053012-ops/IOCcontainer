package th.ac.utc.shop.service;

import org.springframework.stereotype.Service;
import th.ac.utc.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryProductService implements ProductService {
    private final List<Product> products = new ArrayList<>();
    private long nextId = 1;

    public InMemoryProductService() {
        save(new Product(null, "เมาส์ไร้สาย", 390.0, 25));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        products.add(product);
        return product;
    }
}
