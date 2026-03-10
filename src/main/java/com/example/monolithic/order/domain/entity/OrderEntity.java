package com.example.monolithic.order.domain.entity;

import com.example.monolithic.common.domain.BaseTimeEntity;
import com.example.monolithic.product.domain.entity.ProductEntity;
import com.example.monolithic.user.domain.entity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MONOLITHIC_ORDER_TBL")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // FK 이름
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    // 상품 수량
    private Integer qty;

    // 상품 상태
    @Enumerated(EnumType.STRING)
    @Builder.Default // 기본값 세팅을 위한 어노테이션
    private OrderStatus orderStatus = OrderStatus.ORDERED;
}
