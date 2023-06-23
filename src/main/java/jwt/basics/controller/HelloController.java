package jwt.basics.controller;

import jwt.basics.jwtutils.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private TokenManager jwtTokenManager;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}