package com.example.monolithic.order.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.monolithic.order.domain.dto.OrderRequestDTO;
import com.example.monolithic.order.domain.dto.OrderResponseDTO;
import com.example.monolithic.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService ;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderRequestDTO request){
        System.out.println("order ctrl create call");

        OrderResponseDTO response = orderService.orderCreate(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}


