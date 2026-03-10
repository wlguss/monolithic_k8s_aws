package com.example.monolithic.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    
    @GetMapping("/alive")
    public String getMethodName(){
        return "alive";
    }
}
