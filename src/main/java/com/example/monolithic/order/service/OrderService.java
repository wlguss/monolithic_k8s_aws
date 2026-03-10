package com.example.monolithic.order.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.monolithic.order.dao.OrderRepository;
import com.example.monolithic.order.domain.dto.OrderRequestDTO;
import com.example.monolithic.order.domain.dto.OrderResponseDTO;
import com.example.monolithic.order.domain.entity.OrderEntity;
import com.example.monolithic.product.dao.ProductRepository;
import com.example.monolithic.product.domain.entity.ProductEntity;
import com.example.monolithic.user.dao.UserRepository;
import com.example.monolithic.user.domain.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderResponseDTO orderCreate(OrderRequestDTO dto) {
        System.out.println("order service orderCreate call");

        // 로그인된 사용자 정보 가져오기 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth : " + auth.getName());

        UserEntity user = userRepository.findById(auth.getName())
                .orElseThrow(() -> new RuntimeException("user not found"));

        // DTO로 들어오는 상품ID로 product 엔티티 획득 
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("product not found"));

        System.out.println("order service의 재고관리");
        int qty = dto.getQty(); // 구매하고자 하는 수량
        if (product.getStockQty() < qty) {
            throw new RuntimeException(">>> 재고 부족");
        } else {
            // 수량 업데이트
            product.updateStockQty(qty);

        }
        // 주문
        OrderEntity order = orderRepository.save(dto.toEntity(user, product));

        // DTO 반환 
        return OrderResponseDTO.fromEntity(order);
    }
}
