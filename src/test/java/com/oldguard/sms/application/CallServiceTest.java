package com.oldguard.sms.application;

import org.junit.jupiter.api.Test;

class CallServiceTest {
    @Test
    void 전화걸기_테스트(){
        CallService callService=new CallService();
        callService.sendCall();
    }
}