package com.oldguard.sms.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService {
    private final String apiKey="NCSPJIRBL0TOI38C";
    private final String apiSecret="ZI8ZZTNZDSEAYSN0SHZEZENOWI0KHTZX";
    Message message = new Message(apiKey, apiSecret);

    public void sendSms(String phoneNumber,String name,Long time) throws CoolsmsException {
        HashMap<String, String> params = new HashMap<>();
        params.put("to",phoneNumber);
        params.put("from","01083263424");
        params.put("type", "SMS");
        params.put("text", "현재 "+name+"님이 "+time+"시간 동안 반응이 없음을 알려드립니다.");

        //message.send(params);
    }

    public void sendCode(String phoneNumber,String code) throws CoolsmsException {
        HashMap<String, String> params = new HashMap<>();
        params.put("to",phoneNumber);
        params.put("from","01030239006");
        params.put("type", "SMS");
        params.put("text", "[어르신 지킴이] 인증코드 ["+code + "]를 입력해주세요.");

        //message.send(params);
    }


}
