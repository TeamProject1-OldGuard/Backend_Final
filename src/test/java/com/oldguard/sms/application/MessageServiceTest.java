package com.oldguard.sms.application;

import com.twilio.type.PhoneNumber;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.junit.jupiter.api.Test;


public class MessageServiceTest {
    @Test
    void MessageTest() throws CoolsmsException {
        SmsService smsService=new SmsService();
        smsService.sendSms("01040787393","김예린",1L);
    }
}
