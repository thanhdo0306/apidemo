package thanhnt20.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thanhnt20.springboot.model.Product;
import thanhnt20.springboot.model.ResponseObject;
import thanhnt20.springboot.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("list")
    ResponseEntity<ResponseObject> getALlProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Query success", productRepository.findAll()));
    }

    @GetMapping("/{id}")
    // Let's return am object with: data, message, status.
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);

        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Query success", foundProduct)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("FAILED", "Query fail", "")
                );
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        List<Product> foundProductList = productRepository.findByName(newProduct.getName().trim());

        // 2 product must not have same name.
        return foundProductList.size() > 0 ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("FAILED","Product already taken", "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK","Insert success", productRepository.save(newProduct))
                );
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = productRepository.findById(id).map(
                product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setYear(newProduct.getYear());

                    return productRepository.save(product);
                }).orElseGet(() -> {
            //newProduct.setId(id);
            return productRepository.save(newProduct);
        });

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Update success", updatedProduct)
        );

    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);

        if (foundProduct.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK","Delete success", foundProduct));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED","Delete fail", ""));
        }
    }


}
