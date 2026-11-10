package th.ac.utc.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import th.ac.utc.shop.model.Product;
import th.ac.utc.shop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/product/add")
    public Product add(@RequestParam String name,
                       @RequestParam double price,
                       @RequestParam int stock) {
        return productService.save(new Product(null, name, price, stock));
    }
}
