package com.oldguard.guest.presentation;

import com.oldguard.guest.application.GuestService;
import com.oldguard.guest.application.dto.GuestCreateResponse;
import com.oldguard.guest.application.dto.GuestListResponse;
import com.oldguard.guest.application.dto.GuestResponse;
import com.oldguard.guest.presentation.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guest")
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;
    @PostMapping
    @ApiOperation(value = "게스트 회원 가입")
    public ResponseEntity<GuestCreateResponse> joinGuest(@RequestBody @Validated GuestCreateRequest request){
        return ResponseEntity.status(200).body(guestService.joinGuest(request));
    }
    @GetMapping("/list")
    @ApiOperation(value = "게스트 목록 조회")
    public ResponseEntity<List<GuestListResponse>> getGuests(){
        return ResponseEntity.status(200).body(guestService.getGuests());
    }
    @GetMapping
    @ApiOperation(value = "게스트 정보 조회")
    public ResponseEntity<GuestResponse> getGuest(@RequestParam Long id){
        return ResponseEntity.status(200).body(guestService.getGuest(id));
    }
    @PostMapping("/limit")
    @ApiOperation(value = "시간제한 설정")
    public ResponseEntity<String> updateTimeLimit(@RequestBody @Validated TimeLimitRequest request){
        guestService.updateTimeLimit(request);
        return ResponseEntity.status(200).body("시간제한 설정 완료");
    }
    @PatchMapping("/sleep")
    @ApiOperation(value = "수면시간 설정")
    public ResponseEntity<String> updateSleepStartTime(@RequestBody @Validated @DateTimeFormat(pattern = "HH:mm") SleepTimeRequest request) {
        guestService.updateSleepTime(request);
        return ResponseEntity.status(200).body("수면시간 설정 완료");
    }
    @PutMapping
    @ApiOperation(value = "게스트 정보 수정")
    public ResponseEntity<String> editSleepStartTime(@RequestBody @Validated @DateTimeFormat(pattern = "HH:mm") GuestEditRequest request) {
        guestService.editGuest(request);
        return ResponseEntity.status(200).body("게스트 정보 수정 완료");
    }
//    @DeleteMapping
//    @ApiOperation(value = "게스트 정보 삭제")
//    public ResponseEntity<String> deleteGuest(@RequestBody @Validated GuestDeleteRequest request) {
//        guestService.deleteGuest(request);
//        return ResponseEntity.status(200).body("게스트 삭제 완료");
//    }
    @DeleteMapping
    @ApiOperation(value = "게스트 삭제")
    public ResponseEntity<Long> deleteGuest(@RequestBody @Validated GuestDeleteRequest request) {
        return ResponseEntity.status(200).body(guestService.deleteGuest(request));
    }

    @PatchMapping("/restoration")
    @ApiOperation(value = "게스트 정보 복구")
    public ResponseEntity<String> restorationGuest(@RequestBody @Validated GuestRestorationRequest request) {
        guestService.restorationGuest(request);
        return ResponseEntity.status(200).body("게스트 복구 완료");
    }
}

