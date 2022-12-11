package com.oldguard.guest.exception;

import com.oldguard.common.exception.NotFoundException;

public class GuestNotFoundException extends NotFoundException {
    public GuestNotFoundException() {
        super("게스트가 존재하지 않습니다.");
    }
}
