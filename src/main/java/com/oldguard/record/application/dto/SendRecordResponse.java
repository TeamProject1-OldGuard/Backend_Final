package com.oldguard.record.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendRecordResponse {
    private LocalDateTime localDateTime;
    private String type;
    private String name;
    @Builder
    public SendRecordResponse(LocalDateTime localDateTime, String type, String name) {
        this.localDateTime = localDateTime;
        this.type = type;
        this.name = name;
    }
}
