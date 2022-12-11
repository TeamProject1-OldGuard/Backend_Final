package com.oldguard.record.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteRecordResponse {
    private LocalDateTime localDateTime;
    private String name;
    private Long id;

    @Builder
    public DeleteRecordResponse(LocalDateTime localDateTime, String name, Long id) {
        this.localDateTime = localDateTime;
        this.name = name;
        this.id = id;
    }
}
