package com.vecinet.auth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping
    public String main(){
        return "Welcome to vecinet-auth service!";
    }
}
