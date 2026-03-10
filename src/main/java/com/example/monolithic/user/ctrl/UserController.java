package com.example.monolithic.user.ctrl;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.monolithic.user.domain.dto.UserRequestDTO;
import com.example.monolithic.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/users") // naming convention 
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO dto) {
        System.out.println("user ctrl login call");
        
        Map<String, Object> map = userService.login(dto);

        // 토큰 값은 헤더에 저장
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + map.get("access-token"));
        headers.add("Refresh-token", (String) (map.get("refresh-token")));
        // 브라우저는 기본적으로 JS코드가 읽을 수 있는 응답 헤더를 제한하기 때문에 수동으로 허용해줘야 함
        headers.add("Access-Control-Expose-Headers", "Authorization,Refresh-token");

        System.out.println("access token value : " + headers.get("Authorization"));

        if (map.size() != 0) {
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body((String) map.get("access-token"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(null);
        }
    }
}
