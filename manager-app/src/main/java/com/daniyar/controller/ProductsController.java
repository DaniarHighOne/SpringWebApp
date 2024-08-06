package com.daniyar.controller;


import com.daniyar.controller.payload.newProductPayload;
import com.daniyar.entity.Product;
import com.daniyar.restClient.ProductRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")//help to manage overall http
public class ProductsController {

    private final ProductRestClient productRestClient;

    @GetMapping("list")
    public String getProductsList(Model model){
        model.addAttribute("products", this.productRestClient.findAllProducts());
        //go to service, add to model and return it back
        return "catalogue/products/list";
    }
    //create a new form for creating products
    @GetMapping("create")
    public String getNewProductPage(){
        return "catalogue/products/new_product";
    }
    @PostMapping("create")
    public String addNewProduct(newProductPayload payload, Model model) {
        try {
            Product product = this.productRestClient.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/new_product";
        }
    }
}
