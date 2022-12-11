package com.oldguard.contact.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactCreateRequest {
    private Long id;
    private String contact;
    private String name;
}
