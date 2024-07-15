package com.daniyar.controller;


import com.daniyar.controller.payload.newProductPayload;
import com.daniyar.entity.Product;
import com.daniyar.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")//help to manage overall http
public class ProductsController {

    private final ProductService productService;

    @GetMapping("list")
    public String getProductsList(Model model){
        model.addAttribute("products",this.productService.findAllProducts());
        //go to service, add to model and return it back
        return "catalogue/products/list";
    }
    //create a new form for creating products
    @GetMapping("create")
    public String getNewProductPage(){
        return "catalogue/products/new_product";
    }
    @PostMapping("create")
    public String addNewProduct(@Valid newProductPayload payload,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalogue/products/new_product";
        } else {
            Product product = this.productService.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.getId());
        }
    }

}
