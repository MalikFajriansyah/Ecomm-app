package com.project.ecommerceapp.controller;

import com.project.ecommerceapp.dto.ProductDto;
import com.project.ecommerceapp.exceptions.ResourceException;
import com.project.ecommerceapp.model.Product;
import com.project.ecommerceapp.request.AddProductRequest;
import com.project.ecommerceapp.response.ApiResponse;
import com.project.ecommerceapp.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getProducts(){
        List<Product>products = productService.getAllProduct();
        return ResponseEntity.ok(new ApiResponse("Product:", products));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product: ", product));
        } catch (ResourceException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest request){
        try {
            Product newProduct = productService.addProduct(request);
            return ResponseEntity.ok(new ApiResponse("Successfull add products", newProduct));
        } catch (ResourceException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody AddProductRequest request, @PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product updated", product));
        } catch (ResourceException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Error:", "Update failed"));
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Message: ", "Product deleted"));
        } catch (ResourceException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
