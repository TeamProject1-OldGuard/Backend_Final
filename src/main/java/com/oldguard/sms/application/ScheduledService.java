package com.oldguard.sms.application;

import com.oldguard.contact.domain.Contact;
import com.oldguard.guest.domain.Guest;
import com.oldguard.guest.domain.repository.GuestRepository;
import com.oldguard.record.application.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledService {
    private final GuestRepository guestRepository;
    private final SmsService smsService;
    private final CallService callService;
    private final RecordService recordService;

    @Scheduled(cron = "0 * * * * *")
    public void sendSms() throws CoolsmsException {
        log.info("scheduled start");
        List<Guest> guests=guestRepository.findSendSmsGuests();
        log.info(" "+guests.size());
        for(Guest guest:guests){
            for(Contact contact:guest.getContacts()) {
                //smsService.sendSms(contact.getContact(), guest.getName(), guest.getMessageTime());
                recordService.saveSendRecord(guest.getName(),"MESSAGE");
            }
        }
    }
    @Scheduled(cron = "0 * * * * *")
    public void sendCall() throws CoolsmsException {
        log.info("scheduled start");
        List<Guest> guests=guestRepository.findSendCallGuests();
        log.info(" "+guests.size());
        for(Guest guest:guests){
            if(guest.getContacts().get(0).getContact().equals("01083263424")){
                recordService.saveSendRecord(guest.getName(),"CALL");
            }
                callService.sendCall();
        }
    }
}
