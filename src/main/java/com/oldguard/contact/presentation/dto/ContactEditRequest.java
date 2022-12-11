package com.oldguard.contact.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactEditRequest {
    private Long id;
    private String beforeContact;
    private String contact;
    private String name;
}
