package com.oldguard.guest.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestCreateRequest {
    @NotEmpty
    @Column(length = 6)
    private String code;
    private String name;
    private String address;
}
