package com.oldguard.contact.exception;

import com.oldguard.common.exception.NotFoundException;

public class ContactNotFoundException extends NotFoundException {
    public ContactNotFoundException() {
        super("연락처가 존재하지 않습니다.");
    }
}
