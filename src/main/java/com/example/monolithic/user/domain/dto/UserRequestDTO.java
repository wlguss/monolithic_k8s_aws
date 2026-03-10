package com.example.monolithic.user.domain.dto;

import com.example.monolithic.user.domain.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String email;
    private String password;

    // 엔티티로 변환
    public UserEntity toEntity() {
        return UserEntity.builder().email(email).password(password).build();
    }
}
