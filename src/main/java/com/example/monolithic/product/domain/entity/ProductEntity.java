package com.example.monolithic.product.domain.entity;

import com.example.monolithic.common.domain.BaseTimeEntity;
import com.example.monolithic.user.domain.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "MONOLITHIC_PRODUCT_TBL")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column // 주문(order)이 들어온 경우 재고가 있는지를 먼저 확인
    private Integer stockQty;

    // 상품을 등록한 회원(user) 정보 저장 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user ;

    // 재고 관리를 위한 수량 업데이트
    public void updateStockQty(int stockQty) {
        this.stockQty = this.stockQty - stockQty;
    }
}
