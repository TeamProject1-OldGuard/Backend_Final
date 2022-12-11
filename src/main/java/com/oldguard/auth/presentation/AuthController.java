package com.oldguard.auth.presentation;

import com.oldguard.auth.application.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    @ApiOperation(value = "카카오톡 로그인")
    public ResponseEntity<String> kakaoLogin(@RequestParam String token){
        return ResponseEntity.status(200).body(authService.getKakaoUserInfo(token));
    }
}
