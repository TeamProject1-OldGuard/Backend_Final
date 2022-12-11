package com.oldguard.guest.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SleepTimeRequest {
    private Long id;
    private LocalTime sleepStartTime;
    private LocalTime sleepEndTime;
}
