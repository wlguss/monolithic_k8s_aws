package com.example.monolithic.user.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.monolithic.user.dao.UserRepository;
import com.example.monolithic.user.domain.dto.UserRequestDTO;
import com.example.monolithic.user.domain.dto.UserResponseDTO;
import com.example.monolithic.user.domain.entity.UserEntity;
import com.example.monolithic.user.provider.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // redis
    @Qualifier("tokenRedis")
    private final RedisTemplate<String, Object> redisTemplate;
    private static final long REFRESH_TOKEN_TTL = 7 * 60 * 60 * 24;

    public Map<String, Object> login(UserRequestDTO request) {

        // 회원이 존재하는지를 먼저 확인
        UserEntity entity = userRepository.findById(request.getEmail())
                .orElseThrow(() -> new RuntimeException("user not found"));

        // 해싱 처리된 비밀번호 비교
        if (!passwordEncoder.matches(request.getPassword(), entity.getPassword())) {
            throw new RuntimeException("password not matched");
        }

        // 사용자 정보와 생성한 토큰값 저장
        Map<String, Object> map = new HashMap<>();

        // 토큰 생성
        String at = jwtProvider.createAT(entity.getEmail());
        String rt = jwtProvider.CreateRT(entity.getEmail());

        // redis에 refresh-token 저장
        System.out.println("refreshtokenservice save token");
        redisTemplate.opsForValue()
                .set("RT:" + entity.getEmail(), rt, REFRESH_TOKEN_TTL, TimeUnit.SECONDS);

        // 발급한 토큰과 사용자정보 반환
        map.put("response", UserResponseDTO.fromEntity(entity));
        map.put("access-token", at);
        map.put("refresh-token", rt);

        return map;
    }
}
