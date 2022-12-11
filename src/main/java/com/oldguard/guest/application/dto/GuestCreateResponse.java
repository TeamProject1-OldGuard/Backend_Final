package com.oldguard.guest.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestCreateResponse {
    private Long id;
    private String accessToken;

    @Builder
    public GuestCreateResponse(Long id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }
}
