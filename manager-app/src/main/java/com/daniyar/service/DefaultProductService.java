package com.daniyar.service;


import com.daniyar.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService{

    private final ProductRepository productRepository;
}
