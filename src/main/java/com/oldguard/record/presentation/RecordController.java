package com.oldguard.record.presentation;

import com.oldguard.record.application.RecordService;
import com.oldguard.record.application.dto.DeleteRecordResponse;
import com.oldguard.record.application.dto.SendRecordResponse;
import com.oldguard.record.presentation.dto.RecordRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/record")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    @GetMapping("/delete")
    @ApiOperation(value = "삭제한 어르신 기록 조회")
    public ResponseEntity<List<DeleteRecordResponse>> getDeleteRecord(@RequestParam @Validated RecordRequest request){
        return ResponseEntity.status(200).body(recordService.getDeleteRecords(request));
    }

    @GetMapping("/send")
    @ApiOperation(value = "전송한 연락 조회")
    public ResponseEntity<List<SendRecordResponse>> getSendRecord(@RequestParam @Validated RecordRequest request){
        return ResponseEntity.status(200).body(recordService.getSendRecords(request));
    }
}
