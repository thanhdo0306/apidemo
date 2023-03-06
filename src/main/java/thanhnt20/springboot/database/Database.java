package thanhnt20.springboot.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import thanhnt20.springboot.model.Product;
import thanhnt20.springboot.repository.ProductRepository;

@Configuration
public class Database {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Database.class);
    @Bean()
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                Product product01 = new Product("iphone14", 2000.0, 2023);
                Product product02 = new Product("ipadAir", 1800.5, 2022);

                log.info("init data 01" + productRepository.save(product01));
                log.info("init data 01" + productRepository.save(product02));
            }
        };
    }
}
