package com.oldguard.member.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(final String message) {
        super(message);
    }
}
