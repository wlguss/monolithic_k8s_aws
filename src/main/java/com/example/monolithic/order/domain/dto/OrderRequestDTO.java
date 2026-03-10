package com.example.monolithic.order.domain.dto;

import com.example.monolithic.order.domain.entity.OrderEntity;
import com.example.monolithic.product.domain.entity.ProductEntity;
import com.example.monolithic.user.domain.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private int qty ;
    private Long productId;

    public OrderEntity toEntity(UserEntity user, ProductEntity product){
        return OrderEntity.builder().qty(qty).user(user).product(product).build();
    }

}
