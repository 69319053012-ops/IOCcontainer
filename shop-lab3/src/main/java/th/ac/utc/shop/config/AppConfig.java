package th.ac.utc.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.NumberFormat;
import java.util.Locale;

@Configuration
public class AppConfig {
    @Bean
    public NumberFormat priceFormatter() {
        return NumberFormat.getCurrencyInstance(Locale.of("th", "TH"));
    }
}
