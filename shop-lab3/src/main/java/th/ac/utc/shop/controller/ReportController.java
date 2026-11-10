package th.ac.utc.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import th.ac.utc.shop.model.Product;
import th.ac.utc.shop.service.ProductService;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReportController {
    private final ProductService productService;
    private final NumberFormat priceFormatter;

    @GetMapping("/report")
    public Map<String, Object> report() {
        List<Product> all = productService.findAll();
        double total = all.stream().mapToDouble(Product::getTotalValue).sum();
        return Map.of(
                "count", all.size(),
                "totalValue", priceFormatter.format(total)
        );
    }
}
