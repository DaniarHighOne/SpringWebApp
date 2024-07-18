package ag.selm.catalogue.controller;


import ag.selm.catalogue.controller.payload.UpdateProductPayload;
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

import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products/{productId:\\d+}")
public class singleProductController {

    private final ProductService productService;
    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product getProduct(@PathVariable("productId") int productId){
        return this.productService.findProduct(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    //get product by id
    @GetMapping
    public Product findProduct(@ModelAttribute("product") Product product){
        return product;
    }

    //update product by id
    @PatchMapping
    public ResponseEntity<?> updateProduct(@PathVariable("productId")int productId,
                                              @Valid @RequestBody UpdateProductPayload payload,
                                              BindingResult bindingResult,
                                              Locale locale) throws BindException {
        if (bindingResult.hasErrors()) {
            if(bindingResult instanceof BindException exception){
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            this.productService.updateProduct(productId, payload.title(), payload.details());
            return ResponseEntity.noContent()//no content means by this request
                    .build();
        }
    }

    //delete product by id
}