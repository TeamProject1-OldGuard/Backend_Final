package com.oldguard.guest.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GuestListResponse {
    private Long id;
    private String name;
    @Builder
    public GuestListResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
