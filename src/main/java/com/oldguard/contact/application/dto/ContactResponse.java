package com.oldguard.contact.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactResponse {
    @NotEmpty
    private Long contactId;
    @Size(min = 11,max = 13)
    private String contact;
    private String name;
    @Builder
    public ContactResponse(Long contactId,String name, String contact) {
        this.contactId = contactId;
        this.name=name;
        this.contact = contact;
    }
}
