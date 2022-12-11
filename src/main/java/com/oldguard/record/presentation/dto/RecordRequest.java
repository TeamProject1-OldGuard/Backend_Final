package com.oldguard.record.presentation.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordRequest {
    private String sort;
    @Builder
    public RecordRequest(String sort) {
        this.sort = sort;
    }
}
