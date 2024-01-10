package com.example.hcdemo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DemoController {
    @GetMapping("/test")
    public String test() {
        return "test demo";
    }
    

}

