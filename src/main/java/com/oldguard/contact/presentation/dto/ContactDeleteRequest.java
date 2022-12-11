package com.oldguard.contact.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactDeleteRequest {
    private Long id;
    private String contact;
    @NotNull
    private Long contactId;
}
