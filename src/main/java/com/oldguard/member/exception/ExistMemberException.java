package com.oldguard.member.exception;

import com.oldguard.common.exception.InvalidRequestException;

public class ExistMemberException extends InvalidRequestException {
    public ExistMemberException() {
        super("이미 회원가입 된 계정입니다.");
    }
}
