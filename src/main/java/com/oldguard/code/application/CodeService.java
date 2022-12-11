package com.oldguard.code.application;

import com.oldguard.code.domain.Code;
import com.oldguard.code.domain.repository.CodeRepository;
import com.oldguard.code.presentation.dto.CodeCreateRequest;
import com.oldguard.sms.application.SmsService;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeService {
    private static final Integer CODE_LENGTH=6;
    private final CodeRepository codeRepository;
    private final SmsService smsService;
    public void sendCode(CodeCreateRequest request) throws CoolsmsException {
        Code code=Code.builder().code(RandomStringUtils.randomAlphanumeric(CODE_LENGTH)).build();
        codeRepository.save(code);
        smsService.sendCode(request.getPhoneNumber(), code.getCode());
    }
}
