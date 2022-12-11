package com.oldguard.code.exception;

import com.oldguard.common.exception.NotFoundException;

public class CodeNotFoundException extends NotFoundException {
    public CodeNotFoundException() {
        super("일치하는 코드를 찾을 수 없습니다.");
    }
}
