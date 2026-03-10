package com.example.monolithic.product.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.monolithic.product.dao.ProductRepository;
import com.example.monolithic.product.domain.dto.ProductRequestDTO;
import com.example.monolithic.product.domain.dto.ProductResponseDTO;
import com.example.monolithic.product.domain.entity.ProductEntity;
import com.example.monolithic.user.dao.UserRepository;
import com.example.monolithic.user.domain.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository ;
    private final UserRepository userRepository ;

    // 상품 등록(로그인한 사용자 정보가 context holder에 저장되어 있음 !!)
    public ProductResponseDTO productCreate(ProductRequestDTO request){
        System.out.println("product service productCreate call");

        // 로그인된 사용자 정보 가져오기 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth : "+auth.getName()); // 토큰에 심었던 사용자 정보(기본키)인 이메일 획득 

        UserEntity user = userRepository.findById(auth.getName()).orElseThrow(() 
                    -> new RuntimeException("user not found"));
        ProductEntity entity = productRepository.save(request.toEntity(user));
        
        return ProductResponseDTO.fromEntity(entity);

    }
    
}
