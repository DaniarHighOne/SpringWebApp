package ag.selm.catalogue.controller;


import ag.selm.catalogue.controller.payload.newProductPayload;
import ag.selm.catalogue.entity.Product;
import ag.selm.catalogue.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/*
rest controller return response body while simple controller return html resource
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products")
public class ProductsRestController {

    private final ProductService productService;
    private final MessageSource messageSource;

    @GetMapping
    public Iterable<Product> findProducts(){
        return this.productService.findAllProducts();
    }
    //in rest api data is request body annotation
    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody newProductPayload payload,
                                                 BindingResult bindingResult,
                                                 UriComponentsBuilder uriBuilder) throws BindException{
        if (bindingResult.hasErrors()) {
            if(bindingResult instanceof BindException exception){
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
           Product product = this.productService.createProduct(payload.title(),
                   payload.details());
           return ResponseEntity.created(uriBuilder
                           .replacePath("/catalogue-api/products/{productId}")
                           .build(Map.of("productId",product.getId())))
                   .body(product);
        }
    }
}
