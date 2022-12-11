package com.oldguard.guest.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeLimitRequest {
    @NotNull
    private Long id;
    @NotNull
    private Long messageTime;
    @NotNull
    private Long callTime;
    @NotNull
    private Long emergencyTime;
}
