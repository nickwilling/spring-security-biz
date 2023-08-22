package com.example.demo.controller;

import com.example.demo.exception.BizException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @GetMapping("/test")
  public ResponseEntity<?> test(@AuthenticationPrincipal UserDetails userDetails) {
//    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    throw new BizException("500","业务错误");
//    return ResponseEntity.ok()
//            .body(new MessageResponse("You've been tested!"));
  }
}
