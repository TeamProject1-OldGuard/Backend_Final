package com.oldguard.guest.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestEditRequest {
    private Long id;
    private LocalTime sleepStartTime;
    private LocalTime sleepEndTime;
    @NotNull
    private Long messageTime;
    @NotNull
    private Long callTime;
    @NotNull
    private Long emergencyTime;
}
