package com.oldguard.auth.support;


import com.oldguard.auth.exception.TokenNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {
    private SecurityUtil(){
    }
    public static String getCurrentUserEmail(){
        final Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            log.error("Security Context에 인증 정보가 없습니다.");
            throw new TokenNotFoundException();
        }
        return authentication.getName();
    }
}
