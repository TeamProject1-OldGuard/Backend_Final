package com.oldguard.member.presentation;

import com.oldguard.member.application.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @DeleteMapping("/me")
    @ApiOperation(value = "사용자 회원탈퇴")
    public ResponseEntity<String> deleteMember(){
        memberService.deleteMember();
        return ResponseEntity.status(200).body("사용자 회원탈퇴 성공");
    }
}
