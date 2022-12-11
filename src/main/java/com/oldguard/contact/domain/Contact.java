package com.oldguard.contact.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Contact {
    private Long id;
    private String contact;
    private String name;
    public void updateContact(String contact,String name){
        this.contact=contact;
        this.name=name;
    }
    @Builder
    public Contact(Long id,String name,String contact) {
        this.id=id;
        this.name=name;
        this.contact = contact;
    }
}
