package th.ac.utc.shop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {
    private final String shopName;
    private final double vatRate;
    private final Environment environment;

    public InfoController(@Value("${shop.name}") String shopName,
                          @Value("${shop.vat-rate}") double vatRate,
                          Environment environment) {
        this.shopName = shopName;
        this.vatRate = vatRate;
        this.environment = environment;
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        return Map.of(
                "shopName", shopName,
                "vatRate", vatRate,
                "profiles", environment.getActiveProfiles()
        );
    }
}
