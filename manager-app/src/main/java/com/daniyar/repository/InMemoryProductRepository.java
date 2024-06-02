package com.daniyar.repository;

//todo register component inside apllication with stereotypes
//this annotation will help with AOP

import com.daniyar.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InMemoryProductRepository implements ProductRepository{

    //better use this for concurrency
    private final List<Product> productList = Collections.synchronizedList(new LinkedList<>());
}
