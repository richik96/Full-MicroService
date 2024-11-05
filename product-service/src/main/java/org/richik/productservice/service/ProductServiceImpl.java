package org.richik.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.richik.productservice.Exceptions.ProductNotExistsException;
import org.richik.productservice.dto.ProductRequestDto;
import org.richik.productservice.dto.ResponseDto;
import org.richik.productservice.models.Product;
import org.richik.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor        //generates required constructors at compile time
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ResponseDto> getAllProducts() throws ProductNotExistsException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) {
            throw new ProductNotExistsException("No products found");
        }
        return products.stream().map(this::mapToProductResponse).toList();                              //map(product -> mapToProductResponse(product))
    }
    private ResponseDto mapToProductResponse(Product product) {
        return ResponseDto.builder()
                .id(product.getId())
                .price(product.getPrice())
                .description(product.getDescription())
                .name(product.getName())
                .build();
    }

    @Override
    public ResponseDto getSingleProduct(Long id) throws ProductNotExistsException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductNotExistsException("Product with id:" +id + " does not exist!!");
        }
        return mapToProductResponse(productOptional.get());
    }

    @Override
    public Product addNewProduct(ProductRequestDto requestDto) {
        Product product1 = Product.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .description(requestDto.getDescription())
                .build();
        Product productResponse = productRepository.save(product1);
        log.info("Product {} is saved", product1.getId());
        return productResponse;
    }
}
