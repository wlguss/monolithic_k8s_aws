package com.example.monolithic.product.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.monolithic.product.domain.dto.ProductRequestDTO;
import com.example.monolithic.product.domain.dto.ProductResponseDTO;
import com.example.monolithic.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> create(ProductRequestDTO dto) {   // JSON이 아닌 form 데이터가 들어옴 !! 
        System.out.println("product ctrl create call");

        ProductResponseDTO response = productService.productCreate(dto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
