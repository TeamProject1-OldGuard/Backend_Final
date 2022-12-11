package com.oldguard.guest.application.dto;

import com.oldguard.contact.domain.Contact;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
public class GuestResponse {
    private Long id;
    private String guestName;
    private List<Contact> contacts;
    private Long messageTime;
    private Long callTime;
    private Long emergencyTime;
    private LocalTime sleepStartTime;
    private LocalTime sleepEndTime;

    @Builder
    public GuestResponse(Long id,String guestName, List<Contact> contacts, Long messageTime, Long callTime, Long emergencyTime, LocalTime sleepStartTime, LocalTime sleepEndTime) {
        this.id=id;
        this.guestName = guestName;
        this.contacts = contacts;
        this.messageTime = messageTime;
        this.callTime = callTime;
        this.emergencyTime = emergencyTime;
        this.sleepStartTime = sleepStartTime;
        this.sleepEndTime = sleepEndTime;
    }
}
