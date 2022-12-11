package com.oldguard.guest.domain;

import com.oldguard.contact.domain.Contact;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Guest {
    private Long id;
    private String name;
    private List<Contact> contacts=new ArrayList<>();
    private String address;
    private Boolean deleted;
    private LocalDateTime activateTime;
    private Long messageTime;
    private Long callTime;
    private Long emergencyTime;
    private LocalTime sleepStartTime;
    private LocalTime sleepEndTime;
    public void updateMessageTime(Long messageTime){this.messageTime=messageTime;}
    public void updateCallTime(Long callTime){this.callTime=callTime;}
    public void updateEmergencyTime(Long emergencyTime){this.emergencyTime=emergencyTime;}
    public void updateSleepTime(LocalTime sleepStartTime,LocalTime sleepEndTime){
        this.sleepStartTime=sleepStartTime;
        this.sleepEndTime=sleepEndTime;
    }
    public void delete(){
        deleted= true;
    }
    public void restore(){
        deleted=false;
    }
    public void updateContacts(Contact contact){this.contacts.add(contact);}
    @Builder
    public Guest(Long id, String name, List<Contact> contacts, String address, Boolean deleted, LocalDateTime activateTime, Long messageTime, Long callTime, Long emergencyTime, LocalTime sleepStartTime, LocalTime sleepEndTime) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
        this.address = address;
        this.deleted = deleted;
        this.activateTime = activateTime;
        this.messageTime = messageTime;
        this.callTime = callTime;
        this.emergencyTime = emergencyTime;
        this.sleepStartTime = sleepStartTime;
        this.sleepEndTime = sleepEndTime;
    }
}
