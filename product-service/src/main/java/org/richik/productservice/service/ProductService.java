package org.richik.productservice.service;


import org.richik.productservice.Exceptions.ProductNotExistsException;
import org.richik.productservice.dto.ProductRequestDto;
import org.richik.productservice.dto.ResponseDto;
import org.richik.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<ResponseDto> getAllProducts() throws ProductNotExistsException;

    ResponseDto getSingleProduct(Long id) throws ProductNotExistsException;

    Product addNewProduct(ProductRequestDto requestDto);
}
