package com.oldguard.code.presentation;

import com.oldguard.code.application.CodeService;
import com.oldguard.code.presentation.dto.CodeCreateRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/code")
@RequiredArgsConstructor
public class CodeController {
    private final CodeService codeService;
    @PostMapping
    @ApiOperation("인증코드 전송")
    public ResponseEntity<String> sendCode(@RequestBody @Validated CodeCreateRequest request) throws CoolsmsException {
        codeService.sendCode(request);
        return ResponseEntity.status(200).body("인증코드 전송성공");
    }
}
