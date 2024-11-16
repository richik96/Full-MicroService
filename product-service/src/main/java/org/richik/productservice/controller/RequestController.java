package org.richik.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.richik.productservice.Exceptions.ProductNotExistsException;
import org.richik.productservice.dto.ProductRequestDto;
import org.richik.productservice.dto.ResponseDto;
import org.richik.productservice.models.Product;
import org.richik.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class RequestController {

//    @Autowired
    private ProductService productService;
    private ProductRequestDto productRequestDto;

//    public RequestController(ProductService productService) {
//        this.productService = productService;
////        this.productRequestDto = productRequestDto;
//    }

    @GetMapping()
    public ResponseEntity<List<ResponseDto>> getAllProducts() throws ProductNotExistsException {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getSingleProduct(@PathVariable("id") Long id) throws ProductNotExistsException {
        ResponseEntity<ResponseDto> response =  ResponseEntity.status(HttpStatus.FOUND)
                .body(productService.getSingleProduct(id));
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductRequestDto requestDto) {
        ResponseEntity<Product> productResponse = ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productService.addNewProduct(requestDto));
        return productResponse;
    }
}
