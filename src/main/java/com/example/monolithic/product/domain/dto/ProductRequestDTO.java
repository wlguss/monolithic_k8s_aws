package com.example.monolithic.product.domain.dto;

import com.example.monolithic.product.domain.entity.ProductEntity;
import com.example.monolithic.user.domain.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter     // form 데이터로 받는 경우 반드시 추가 (JSON을 지향하는 이유)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    private String name;
    private int price;
    private int stockQty;

    // 사용자 정보는 테이블에서 가져오는 것이 아닌 로그인 이후 발급받은 토큰 정보로 획득 (context holder에 저장된 사용자 정보)
    public ProductEntity toEntity(UserEntity entity) {
        return ProductEntity.builder().name(name).price(price).stockQty(stockQty)
                .user(entity).build();
    }
}
