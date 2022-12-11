package com.oldguard.auth.exception;

import com.oldguard.member.exception.UnauthorizedException;

public class TokenNotFoundException extends UnauthorizedException {
    public TokenNotFoundException() {
        super("토큰이 존재하지 않습니다.");
    }
}
