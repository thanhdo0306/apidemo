package thanhnt20.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thanhnt20.springboot.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String strName);
}
