package com.oldguard.sms.application;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;


@Service
public class CallService {
    private final String SID="ACa61b790e5e2a275d527b126ca7876018";
    private final String TOKEN="0d7d53c560ea5a92f60c7ea26873aeaf";
    private final String FROM="+18318511729";
    public CallService(){
        Twilio.init(SID,TOKEN);
    }

    public void sendCall(){
        //Call.creator(new PhoneNumber("+821083263424"),new PhoneNumber(FROM), "APfa71296cccc7f6ad59d2aa3fa375badf").create();
    }
}
