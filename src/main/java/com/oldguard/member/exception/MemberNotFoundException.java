package com.oldguard.member.exception;

import com.oldguard.common.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException() {
        super("일치하는 회원을 찾을 수 없습니다.");
    }
}
