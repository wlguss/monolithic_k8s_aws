package com.example.monolithic.common.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass   // 다른 엔티티에서 상속받을 수 있도록 시간과 관련된 정보 관리(테이블이 생성되지는 않음)
@Getter
public class BaseTimeEntity {
    @CreationTimestamp
    private LocalDateTime createAt;
    @CreationTimestamp
    private LocalDateTime updateAt;

    private LocalDateTime deleteAt;
}
